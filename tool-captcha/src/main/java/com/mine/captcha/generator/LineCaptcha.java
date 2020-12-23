package com.mine.captcha.generator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 使用干扰线方式生成的图形验证码
 */

public class LineCaptcha extends AbstractCaptcha {
	private static final long serialVersionUID = 8691294460763091089L;

	/**
	 * RGB颜色范围上限
	 */
	private static final int RGB_COLOR_BOUND = 256;

	/**
	 * 构造，默认4位验证码，150条干扰线
	 * 
	 * @param width 图片宽
	 * @param height 图片高
	 */
	public LineCaptcha(int width, int height) {
		this(width, height, 4, 150);
	}

	/**
	 * 构造
	 * 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param codeCount 字符个数
	 * @param lineCount 干扰线条数
	 */
	public LineCaptcha(int width, int height, int codeCount, int lineCount) {
		super(width, height, codeCount, lineCount);
	}

	@Override
	public Image createImage(String code) {
		// 图像buffer
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g = createGraphics(image, defaultIfNull(this.background, Color.WHITE));
		// 干扰线
		drawInterfere(g);
		// 字符串
		drawString(g, code);
		return image;
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

	public static <T> T defaultIfNull(final T object, final T defaultValue) {
		return (null != object) ? object : defaultValue;
	}

	/**
	 * 绘制字符串
	 * 
	 * @param g {@link Graphics}画笔
	 * @param code 验证码
	 */
	private void drawString(Graphics2D g, String code) {
		// 指定透明度
		if (null != this.textAlpha) {
			g.setComposite(this.textAlpha);
		}
		drawString(g, code, this.font, null, this.width, this.height);
	}

	public static Graphics drawString(Graphics g, String str, Font font, Color color, int width, int height) {
		// 抗锯齿
		if (g instanceof Graphics2D) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		// 创建字体
		g.setFont(font);

		// 文字高度（必须在设置字体后调用）
		int midY = getCenterY(g, height);
		if (null != color) {
			g.setColor(color);
		}

		final int len = str.length();
		int charWidth = width / len;
		for (int i = 0; i < len; i++) {
			if (null == color) {
				// 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
				Random random = ThreadLocalRandom.current();
				g.setColor(new Color(random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND)));
			}
			g.drawString(String.valueOf(str.charAt(i)), i * charWidth, midY);
		}
		return g;
	}

	public static int getCenterY(Graphics g, int backgroundHeight) {
		// 获取允许文字最小高度
		FontMetrics metrics = null;
		try {
			metrics = g.getFontMetrics();
		} catch (Exception e) {
			// 此处报告bug某些情况下会抛出IndexOutOfBoundsException，在此做容错处理
		}
		int y;
		if (null != metrics) {
			y = (backgroundHeight - metrics.getHeight()) / 2 + metrics.getAscent();
		} else {
			y = backgroundHeight / 3;
		}
		return y;
	}

	/**
	 * 绘制干扰线
	 * 
	 * @param g {@link Graphics2D}画笔
	 */
	private void drawInterfere(Graphics2D g) {
		final ThreadLocalRandom random = ThreadLocalRandom.current();
		// 干扰线
		for (int i = 0; i < this.interfereCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width / 8);
			int ye = ys + random.nextInt(height / 8);
			g.setColor(new Color(random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND), random.nextInt(RGB_COLOR_BOUND)));
			g.drawLine(xs, ys, xe, ye);
		}
	}
}