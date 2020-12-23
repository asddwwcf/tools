package com.mine.captcha.generator;

import org.springframework.util.StringUtils;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

/**
 * 抽象验证码<br>
 * 抽象验证码实现了验证码字符串的生成、验证，验证码图片的写出<br>
 * 实现类通过实现{@link #createImage(String)} 方法生成图片对象
 *
 * @author looly
 */
public abstract class AbstractCaptcha implements ICaptcha {
	private static final long serialVersionUID = 3180820918087507254L;

	// 图形交换格式
	public static final String IMAGE_TYPE_GIF = "gif";
	// 联合照片专家组
	public static final String IMAGE_TYPE_JPG = "jpg";
	// 联合照片专家组
	public static final String IMAGE_TYPE_JPEG = "jpeg";
	// 英文Bitmap（位图）的简写，它是Windows操作系统中的标准图像文件格式
	public static final String IMAGE_TYPE_BMP = "bmp";
	// 可移植网络图形
	public static final String IMAGE_TYPE_PNG = "png";
	// Photoshop的专用格式Photoshop
	public static final String IMAGE_TYPE_PSD = "psd";

	/**
	 * 图片的宽度
	 */
	protected int width;
	/**
	 * 图片的高度
	 */
	protected int height;
	/**
	 * 验证码干扰元素个数
	 */
	protected int interfereCount;
	/**
	 * 字体
	 */
	protected Font font;
	/**
	 * 验证码
	 */
	protected String code;
	/**
	 * 验证码图片
	 */
	protected byte[] imageBytes;
	/**
	 * 验证码生成器
	 */
	protected CodeGenerator generator;
	/**
	 * 背景色
	 */
	protected Color background;
	/**
	 * 文字透明度
	 */
	protected AlphaComposite textAlpha;

	/**
	 * 构造，使用随机验证码生成器生成验证码
	 *
	 * @param width          图片宽
	 * @param height         图片高
	 * @param codeCount      字符个数
	 * @param interfereCount 验证码干扰元素个数
	 */
	public AbstractCaptcha(int width, int height, int codeCount, int interfereCount) {
		this(width, height, new RandomGenerator(codeCount), interfereCount);
	}

	/**
	 * 构造
	 *
	 * @param width          图片宽
	 * @param height         图片高
	 * @param generator      验证码生成器
	 * @param interfereCount 验证码干扰元素个数
	 */
	public AbstractCaptcha(int width, int height, CodeGenerator generator, int interfereCount) {
		this.width = width;
		this.height = height;
		this.generator = generator;
		this.interfereCount = interfereCount;
		// 字体高度设为验证码高度-2，留边距
		this.font = new Font(Font.SANS_SERIF, Font.PLAIN, (int) (this.height * 0.75));
	}

	@Override
	public void createCode() throws IOException {
		generateCode();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		writePng(createImage(this.code), out);
		this.imageBytes = out.toByteArray();
	}

	public static void writePng(Image image, OutputStream out) throws IOException {
		write(image, IMAGE_TYPE_PNG, out);
	}
	public static void write(Image image, String imageType, OutputStream out) throws IOException {
		write(image, imageType, getImageOutputStream(out));
	}
	public static ImageOutputStream getImageOutputStream(OutputStream out) throws IOException {
		ImageOutputStream result;
		try {
			result = ImageIO.createImageOutputStream(out);
		} catch (IOException e) {
			throw new IOException(e);
		}
		if (null == result) {
			throw new IllegalArgumentException("Image type is not supported!");
		}
		return result;
	}
	public static boolean write(Image image, String imageType, ImageOutputStream destImageStream) throws IOException {
		return write(image, imageType, destImageStream, 1);
	}
	public static boolean write(Image image, String imageType, ImageOutputStream destImageStream, float quality) throws IOException {
		if (StringUtils.isEmpty(imageType)) {
			imageType = IMAGE_TYPE_JPG;
		}

		final BufferedImage bufferedImage = toBufferedImage(image, imageType);
		final ImageWriter writer = getWriter(bufferedImage, imageType);
		return write(bufferedImage, writer, destImageStream, quality);
	}
	public static ImageWriter getWriter(Image img, String formatName) {
		final ImageTypeSpecifier type = ImageTypeSpecifier.createFromRenderedImage(toBufferedImage(img, formatName));
		final Iterator<ImageWriter> iter = ImageIO.getImageWriters(type, formatName);
		return iter.hasNext() ? iter.next() : null;
	}
	public static BufferedImage toBufferedImage(Image image, String imageType) {
		final int type = imageType.equalsIgnoreCase(IMAGE_TYPE_PNG)
				? BufferedImage.TYPE_INT_ARGB
				: BufferedImage.TYPE_INT_RGB;
		return toBufferedImage(image, type);
	}
	public static BufferedImage toBufferedImage(Image image, int imageType) {
		BufferedImage bufferedImage;
		if (image instanceof BufferedImage) {
			bufferedImage = (BufferedImage) image;
			if (imageType != bufferedImage.getType()) {
				bufferedImage = copyImage(image, imageType);
			}
		} else {
			bufferedImage = copyImage(image, imageType);
		}
		return bufferedImage;
	}
	public static BufferedImage copyImage(Image img, int imageType) {
		return copyImage(img, imageType, null);
	}
	public static BufferedImage copyImage(Image img, int imageType, Color backgroundColor) {
		final BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), imageType);
		final Graphics2D bGr = createGraphics(bimage, backgroundColor);
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}
	public static Graphics2D createGraphics(BufferedImage image, Color color) {
		final Graphics2D g = image.createGraphics();
		if (null != color) {
			// 填充背景
			g.setColor(color);
			g.fillRect(0, 0, image.getWidth(), image.getHeight());
		}
		return g;
	}
	public static boolean write(Image image, ImageWriter writer, ImageOutputStream output, float quality) throws IOException {
		if (writer == null) {
			return false;
		}
		writer.setOutput(output);
		final RenderedImage renderedImage = toRenderedImage(image);
		// 设置质量
		ImageWriteParam imgWriteParams = null;
		if (quality > 0 && quality < 1) {
			imgWriteParams = writer.getDefaultWriteParam();
			if (imgWriteParams.canWriteCompressed()) {
				imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
				imgWriteParams.setCompressionQuality(quality);
				final ColorModel colorModel = renderedImage.getColorModel();
				imgWriteParams.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			}
		}
		try {
			if (null != imgWriteParams) {
				writer.write(null, new IIOImage(renderedImage, null, null), imgWriteParams);
			} else {
				writer.write(renderedImage);
			}
			output.flush();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			writer.dispose();
		}
		return true;
	}
	public static RenderedImage toRenderedImage(Image img) {
		if (img instanceof RenderedImage) {
			return (RenderedImage) img;
		}
		return copyImage(img, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * 生成验证码字符串
	 *
	 * @since 3.3.0
	 */
	protected void generateCode() {
		this.code = generator.generate();
	}

	/**
	 * 根据生成的code创建验证码图片
	 *
	 * @param code 验证码
	 * @return Image
	 */
	protected abstract Image createImage(String code);

	@Override
	public String getCode() throws IOException {
		if (null == this.code) {
			createCode();
		}
		return this.code;
	}

	@Override
	public boolean verify(String userInputCode) throws IOException {
		return this.generator.verify(getCode(), userInputCode);
	}

	/**
	 * 验证码写出到文件
	 *
	 * @param path 文件路径
	 * @throws IOException IO异常
	 */
	public void write(String path) throws IOException {
		this.write(touch(path));
	}
	public static File touch(String fullFilePath) throws IOException {
		if (fullFilePath == null) {
			return null;
		}
		return touch(file(fullFilePath));
	}
	public static File file(String path) throws IOException {
		if (null == path) {
			return null;
		}
		return new File(getAbsolutePath(path));
	}
	public static String getAbsolutePath(String path) throws IOException {
		return getAbsolutePath(path, null);
	}
	public static String getAbsolutePath(String path, Class<?> baseClass) throws IOException {
		String normalPath;
		if (path == null) {
			normalPath = "";
		} else {
			normalPath = normalize(path);
			if (isAbsolutePath(normalPath)) {
				// 给定的路径已经是绝对路径了
				return normalPath;
			}
		}
		// 相对于ClassPath路径
		final URL url = getResource(normalPath, baseClass);
		if (null != url) {
			// 对于jar中文件包含file:前缀，需要去掉此类前缀，在此做标准化，since 3.0.8 解决中文或空格路径被编码的问题
			return normalize(getDecodedPath(url));
		}
		// 如果资源不存在，则返回一个拼接的资源绝对路径
		final String classPath = getClassPath(false);
		if (null == classPath) {
			// throw new NullPointerException("ClassPath is null !");
			// 在jar运行模式中，ClassPath有可能获取不到，此时返回原始相对路径（此时获取的文件为相对工作目录）
			return path;
		}
		// 资源不存在的情况下使用标准化路径有问题，使用原始路径拼接后标准化路径
		return normalize(classPath.concat(Objects.requireNonNull(path)));
	}
	public static String getClassPath(boolean isEncoded) throws IOException {
		final URL classPathURL = getClassPathURL();
		String url = isEncoded ? classPathURL.getPath() : getDecodedPath(classPathURL);
		return normalize(url);
	}
	public static URI toURI(URL url) throws Exception {
		return toURI(url, false);
	}
	public static URI toURI(URL url, boolean isEncode) throws Exception {
		if (null == url) {
			return null;
		}

		return toURI(url.toString(), isEncode);
	}
	public static URI toURI(String location, boolean isEncode) throws Exception {
		if (isEncode) {
			location = encode(location);
		}
		try {
			return new URI(StringUtils.trimWhitespace(location));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	public static String encode(String url){
		return encode(url, StandardCharsets.UTF_8);
	}
	public static String encode(String path, Charset charset) {
		final StringBuilder rewrittenPath = new StringBuilder(path.length());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(buf, charset);
		int c;
		for (int i = 0; i < path.length(); i++) {
			c = path.charAt(i);
			if (safeCharacters.get(c)) {
				rewrittenPath.append((char) c);
			} else if (encodeSpaceAsPlus && c == CharUtil.SPACE) {
				// 对于空格单独处理
				rewrittenPath.append('+');
			} else {
				// convert to external encoding before hex conversion
				try {
					writer.write((char) c);
					writer.flush();
				} catch (IOException e) {
					buf.reset();
					continue;
				}
				byte[] ba = buf.toByteArray();
				for (byte toEncode : ba) {
					// Converting each byte in the buffer
					rewrittenPath.append('%');
					HexUtil.appendHex(rewrittenPath, toEncode, false);
				}
				buf.reset();
			}
		}
		return rewrittenPath.toString();
	}
	public static URL getClassPathURL() throws IOException {
		return getResourceURL("");
	}
	public static URL getResourceURL(String resource) throws IOException {
		return getResource(resource);
	}
	public static URL getResource(String resource) throws IOException {
		return getResource(resource, null);
	}
	public static String getDecodedPath(URL url) {
		if (null == url) {
			return null;
		}
		String path = null;
		try {
			// URL对象的getPath方法对于包含中文或空格的问题
			path = toURI(url).getPath();
		} catch (Exception e) {
			// ignore
		}
		return (null != path) ? path : url.getPath();
	}
	public static URL getResource(String resource, Class<?> baseClass) {
		return (null != baseClass) ? baseClass.getResource(resource) : getClassLoader().getResource(resource);
	}
	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = AbstractCaptcha.class.getClassLoader();
			if (null == classLoader) {
				classLoader = ClassLoader.getSystemClassLoader();
			}
		}
		return classLoader;
	}
	public static boolean isAbsolutePath(String path) {
		if (StringUtils.isEmpty(path)) {
			return false;
		}
		// 给定的路径已经是绝对路径了
		return '/' == path.charAt(0) || path.matches("^[a-zA-Z]:([/\\\\].*)?");
	}
	public static File touch(File file) throws IOException {
		if (null == file) {
			return null;
		}
		if (false == file.exists()) {
			mkParentDirs(file);
			try {
				file.createNewFile();
			} catch (Exception e) {
				throw new IOException(e);
			}
		}
		return file;
	}
	public static File mkParentDirs(File file) {
		final File parentFile = file.getParentFile();
		if (null != parentFile && false == parentFile.exists()) {
			parentFile.mkdirs();
		}
		return parentFile;
	}
	public static String normalize(String path) {
		if (path == null) {
			return null;
		}
		// 兼容Spring风格的ClassPath路径，去除前缀，不区分大小写
		String pathToUse = StrUtil.removePrefixIgnoreCase(path, URLUtil.CLASSPATH_URL_PREFIX);
		// 去除file:前缀
		pathToUse = StrUtil.removePrefixIgnoreCase(pathToUse, URLUtil.FILE_URL_PREFIX);
		// 识别home目录形式，并转换为绝对路径
		if (pathToUse.startsWith("~")) {
			pathToUse = pathToUse.replace("~", getUserHomePath());
		}
		// 统一使用斜杠
		pathToUse = pathToUse.replaceAll("[/\\\\]+", StrUtil.SLASH).trim();
		//兼容Windows下的共享目录路径（原始路径如果以\\开头，则保留这种路径）
		if (path.startsWith("\\\\")) {
			pathToUse = "\\" + pathToUse;
		}
		String prefix = "";
		int prefixIndex = pathToUse.indexOf(StrUtil.COLON);
		if (prefixIndex > -1) {
			// 可能Windows风格路径
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (StrUtil.startWith(prefix, StrUtil.C_SLASH)) {
				// 去除类似于/C:这类路径开头的斜杠
				prefix = prefix.substring(1);
			}
			if (false == prefix.contains(StrUtil.SLASH)) {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			} else {
				// 如果前缀中包含/,说明非Windows风格path
				prefix = StrUtil.EMPTY;
			}
		}
		if (pathToUse.startsWith(StrUtil.SLASH)) {
			prefix += StrUtil.SLASH;
			pathToUse = pathToUse.substring(1);
		}

		java.util.List<String> pathList = StrUtil.split(pathToUse, StrUtil.C_SLASH);
		List<String> pathElements = new LinkedList<>();
		int tops = 0;
		String element;
		for (int i = pathList.size() - 1; i >= 0; i--) {
			element = pathList.get(i);
			// 只处理非.的目录，即只处理非当前目录
			if (false == StrUtil.DOT.equals(element)) {
				if (StrUtil.DOUBLE_DOT.equals(element)) {
					tops++;
				} else {
					if (tops > 0) {
						// 有上级目录标记时按照个数依次跳过
						tops--;
					} else {
						// Normal path element found.
						pathElements.add(0, element);
					}
				}
			}
		}

		return prefix + CollUtil.join(pathElements, StrUtil.SLASH);
	}

	/**
	 * 验证码写出到文件
	 *
	 * @param file 文件
	 * @throws IOException IO异常
	 */
	public void write(File file) throws IOException {
		try (OutputStream out = FileUtil.getOutputStream(file)) {
			this.write(out);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void write(OutputStream out) {
		IoUtil.write(out, false, getImageBytes());
	}

	/**
	 * 获取图形验证码图片bytes
	 *
	 * @return 图形验证码图片bytes
	 * @since 4.5.17
	 */
	public byte[] getImageBytes() {
		if (null == this.imageBytes) {
			createCode();
		}
		return this.imageBytes;
	}

	/**
	 * 获取验证码图
	 *
	 * @return 验证码图
	 */
	public BufferedImage getImage() {
		return ImgUtil.read(IoUtil.toStream(getImageBytes()));
	}

	/**
	 * 获得图片的Base64形式
	 *
	 * @return 图片的Base64
	 * @since 3.3.0
	 */
	public String getImageBase64() {
		return Base64.encode(getImageBytes());
	}

	/**
	 * 获取图片带文件格式的 Base64
	 *
	 * @return 图片带文件格式的 Base64
	 * @since 5.3.11
	 */
	public String getImageBase64Data(){
		return URLUtil.getDataUriBase64("image/png", getImageBase64());
	}

	/**
	 * 自定义字体
	 *
	 * @param font 字体
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * 获取验证码生成器
	 *
	 * @return 验证码生成器
	 */
	public CodeGenerator getGenerator() {
		return generator;
	}

	/**
	 * 设置验证码生成器
	 *
	 * @param generator 验证码生成器
	 */
	public void setGenerator(CodeGenerator generator) {
		this.generator = generator;
	}

	/**
	 * 设置背景色
	 *
	 * @param background 背景色
	 * @since 4.1.22
	 */
	public void setBackground(Color background) {
		this.background = background;
	}

	/**
	 * 设置文字透明度
	 *
	 * @param textAlpha 文字透明度，取值0~1，1表示不透明
	 * @since 4.5.17
	 */
	public void setTextAlpha(float textAlpha) {
		this.textAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, textAlpha);
	}

}
