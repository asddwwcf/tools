package com.mine.tool.common.example;

/**
 * Title: CountDownLatchExample
 * Description:
 * Created by admin on 2021/8/3.
 */
public class CountDownLatchExample {

    /**
     * 多线程查询
     */
//    private Map<String, List<VehicleIncrease>> getSubdivDateIncreaseListMap(VehSubdivisionParam vehSubdivisionParam, List<String> querySubdivisions) {
//        CountDownLatch countDownLatch = new CountDownLatch(querySubdivisions.size());
//        //细分市场每日新车数据
//        Map<String, List<VehicleIncrease>> subdivDateIncreaseListMap = new ConcurrentHashMap<>();
//        querySubdivisions.forEach(subdivision -> {
//            //利用多线程查细分市场每日新车数据，提升性能
//            ExecutorFactory.execute(() -> {
//                VehSubdivisionParam queryParam = new VehSubdivisionParam();
//                BeanUtils.copyProperties(vehSubdivisionParam, queryParam);
//                queryParam.setSubdivisions(Lists.newArrayList(subdivision));
//                List<VehicleIncrease> dateIncreaseList = getDayIncreaseList(queryParam);
//                subdivDateIncreaseListMap.put(subdivision, dateIncreaseList);
//                countDownLatch.countDown();
//            });
//        });
//        try {
//            //等待全部数据查询完成后，在进行下一步
//            countDownLatch.await(10, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            logger.error("多线程查询主销区域与行业总销量数据出错，error msg: {}，stack: {}", e.getMessage(), JSONObject.toJSONString(e.getStackTrace()));
//        }
//        return subdivDateIncreaseListMap;
//    }
}
