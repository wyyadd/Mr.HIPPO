package com.hippo.fresh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.dao.FavoriteRepository;
import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.FavoriteService;
import com.hippo.fresh.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@EnableScheduling
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    /**增加商品到收藏夹*/
    public ResponseUtils save(Long userId, Long productId){

        JSONObject jsonObject = new JSONObject();
        Optional<Product> p = productRepository.findById(productId);
        //如果商品不存在
        if(!p.isPresent()){
            return ResponseUtils.response(404, "收藏夹添加商品不存在", jsonObject);
        }
        else{
            //该用户已收藏该商品
            if(favoriteRepository.findByUserIdAndProductId(userId,productId).isPresent()){
                return ResponseUtils.response(405, "你已收藏该商品", jsonObject);
            }
            else {
                Product product = p.get();
                Timestamp creatTime = new Timestamp(System.currentTimeMillis());
                favoriteRepository.save(new Favorite(userId, productId, product.getName(),product.getPictureUrl(),
                        product.getPrice(),product.getPrice(),product.getPrice(), product.getStatus(),
                        0,0,0,0, creatTime));
                return ResponseUtils.response(200, "收藏夹添加商品成功", jsonObject);
            }
        }
    }


    /**从收藏夹中删除商品*/
    public ResponseUtils delete(Long userId, Long productId){

        JSONObject jsonObject = new JSONObject();

        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndProductId(userId,productId);

        //该用户收藏夹中不存在该商品
        if(!favorite.isPresent()){
            return ResponseUtils.response(404, "收藏夹删除商品失败", jsonObject);
        }
        else{
            favoriteRepository.deleteById(favorite.get().getId());
            return ResponseUtils.response(200, "收藏夹删除商品成功", jsonObject);
        }

    }


    /**查找收藏夹商品*/
    public ResponseUtils find(Long userId){
        JSONObject jsonObject = new JSONObject();

        List<Favorite> favorites = favoriteRepository.findAllByUserIdOrderByCreateTimeDesc(userId);
        List<Map<String,Object>> res = new ArrayList<>();

        for(Favorite favorite:favorites){
            Map<String,Object> map= new HashMap<>();
            map.put("productId",favorite.getProductId());
            map.put("productName",favorite.getProductName());
            map.put("productPicture",favorite.getProductPicture());
            map.put("oldPrice",favorite.getOldPrice());
            map.put("currentPrice",favorite.getCurrentPrice());
            map.put("productStatus",favorite.getStatus());
            res.add(map);
        }
        jsonObject.put("products",res);
        return ResponseUtils.response(200, "收藏夹查找成功", jsonObject);

    }


    /**时间差计算*/
    public int timeDifference(Timestamp createTime, Timestamp nowTime) {
        long t1 = createTime.getTime();
        long t2 = nowTime.getTime();
        int days = (int) ((t2 - t1)/(1000*60*60*24));
        int hours=(int) ((t2 - t1)/(1000*60*60));
//      int minutes=(int) (((t2 - t1)/1000-hours*(60*60))/60);
//      int second=(int) ((t2 - t1)/1000-hours*(60*60)-minutes*60);
        return days;
    }

    /**收藏夹商品降价10%提醒*/
    public boolean priceDrop10Reminder(Favorite favorite){
            //该用户收藏夹中该商品未收到过降价10%通知
            if(favorite.getNotifyTen() == 0) {
                double oldPrice = Double.parseDouble(favorite.getOldPrice().toString());
                double currentPrice = Double.parseDouble(favorite.getCurrentPrice().toString());
                double priceDifference = (oldPrice - currentPrice )/ oldPrice;
                //商品降价幅度超过10%
                if (priceDifference > 0.1) {
                    int days = timeDifference(favorite.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                    //用户收藏该商品时间小于10天
                    if (days < 10) {
//                        System.out.println("用户id：" + favorite.getUserId() + " 商品id： " + favorite.getProductId() + "降价10%提醒！");

                        //将消息通知保存到数据库
                        String message = "尊敬的用户你好:  你收藏的商品：  " + favorite.getProductName() + "  加入收藏夹以来降价幅度超过10%，快来关注一下吧！！";
                        Timestamp createTime = new Timestamp(System.currentTimeMillis());
                        noticeRepository.save(new Notice(favorite.getUserId(), message, createTime,0));

                        //更新收藏夹中该商品降价10%通知状态为：已通知
                        favorite.setNotifyTen(1);
                        favoriteRepository.save(favorite);
                        return true;
                    }
                }
            }
            return false;
    }

    /**收藏夹商品降价20%提醒*/
    public boolean priceDrop20Reminder(Favorite favorite){
            //该用户收藏夹中该商品未收到过降价20%通知
            if(favorite.getNotifyTwenty() == 0) {
                double oldPrice = Double.parseDouble(favorite.getOldPrice().toString());
                double currentPrice = Double.parseDouble(favorite.getCurrentPrice().toString());
                double priceDifference = (oldPrice - currentPrice )/ oldPrice;
                //商品降价幅度超过20%
                if (priceDifference > 0.2) {
                    int days = timeDifference(favorite.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                    //用户收藏该商品时间小于20天
                    if (days < 20) {
//                        System.out.println("用户id：" + favorite.getUserId() + " 商品id： " + favorite.getProductId() + "降价20%提醒！");

                        //将消息通知保存到数据库
                        String message = "尊敬的用户你好:  你收藏的商品：  " + favorite.getProductName() + "  加入收藏夹以来降价幅度超过20%，快来关注一下吧！！";
                        Timestamp createTime = new Timestamp(System.currentTimeMillis());
                        noticeRepository.save(new Notice(favorite.getUserId(), message, createTime,0));

                        //更新收藏夹中该商品降价10%通知状态为：已通知
                        favorite.setNotifyTwenty(1);
                        favorite.setNotifyTen(1);
                        favoriteRepository.save(favorite);
                        return true;
                    }
                }
            }
            return false;
    }


    /**收藏夹商品降价30%提醒*/
    public boolean priceDrop30Reminder(Favorite favorite){
            //该用户收藏夹中该商品未收到过降价30%通知
            if(favorite.getNotifyThirty() == 0) {
                double oldPrice = Double.parseDouble(favorite.getOldPrice().toString());
                double currentPrice = Double.parseDouble(favorite.getCurrentPrice().toString());
                double priceDifference = (oldPrice - currentPrice )/ oldPrice;
                //商品降价幅度超过30%
                if (priceDifference > 0.3) {
                    int days = timeDifference(favorite.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                    //用户收藏该商品时间小于30天
                    if (days < 30) {
//                        System.out.println("用户id：" + favorite.getUserId() + " 商品id： " + favorite.getProductId() + "降价30%提醒！");

                        //将消息通知保存到数据库
                        String message = "尊敬的用户你好:  你收藏的商品：  " + favorite.getProductName() + "  加入收藏夹以来降价幅度超过30%，快来关注一下吧！！";
                        Timestamp createTime = new Timestamp(System.currentTimeMillis());
                        noticeRepository.save(new Notice(favorite.getUserId(), message, createTime,0));

                        //更新收藏夹中该商品降价30%通知状态为：已通知
                        favorite.setNotifyThirty(1);
                        favorite.setNotifyTwenty(1);
                        favorite.setNotifyTen(1);
                        favoriteRepository.save(favorite);
                        return true;
                    }
                }
            }
            return false;
    }



    /**收藏夹商品最低价格提醒*/
    public boolean priceLowestReminder(Favorite favorite){

        //该用户收藏夹中该商品未收到过最低价格通知
        if(favorite.getNotifyLowest() == 0) {
            double oldPrice = Double.parseDouble(favorite.getOldPrice().toString());
            double currentPrice = Double.parseDouble(favorite.getCurrentPrice().toString());
            double lowestPrice = Double.parseDouble(favorite.getLowestPrice().toString());
            //商品目前价格为加入购物车最低价格
            if (currentPrice < oldPrice && currentPrice == lowestPrice) {
                int days = timeDifference(favorite.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                //用户收藏该商品时间小于30天
                if (days < 30) {
//                    System.out.println("用户id：" + favorite.getUserId() + " 商品id： " + favorite.getProductId() + "最低价格提醒！");

                    //将消息通知保存到数据库
                    String message = "尊敬的用户你好:  你收藏的商品：  " + favorite.getProductName() + "  目前达到加入收藏夹以来最低价格，快来关注一下吧！！";
                    Timestamp createTime = new Timestamp(System.currentTimeMillis());
                  noticeRepository.save(new Notice(favorite.getUserId(), message, createTime,0));

                    //更新收藏夹中该商品最低价格通知状态为：已通知
                    favorite.setNotifyLowest(1);
                    favoriteRepository.save(favorite);
                    return true;
                }
            }
        }
        return false;
    }


}
