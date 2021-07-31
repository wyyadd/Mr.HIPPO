package com.hippo.fresh.service.impl;

import com.hippo.fresh.dao.CartRepository;
import com.hippo.fresh.dao.NoticeRepository;
import com.hippo.fresh.dao.ProductRepository;
import com.hippo.fresh.entity.Cart;
import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Notice;
import com.hippo.fresh.entity.Product;
import com.hippo.fresh.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CartServiceImpl  implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    /**根据用户id查找购物车*/
    public List<Map<String,Object>> findCartInformationByUserId(Long userId){
        List<Map<String,Object>> res = new ArrayList<>();
        List<Cart> carts = cartRepository.findByUserIdOrderByUpdateTimeDesc(userId);
        for(Cart cart:carts){
            Map<String,Object> map = new HashMap<>();
            map.put("productId",cart.getProductId());
            map.put("productName",cart.getProductName());
            map.put("productPicture",cart.getProductPicture());
            map.put("OldPrice",cart.getOldPrice());
            map.put("currentPrice",cart.getCurrentPrice());
            map.put("productQuantity",cart.getQuantity());
            map.put("productStatus",cart.getStatus());
            res.add(map);
        }
        return res;
    }


    /**根据用户id、商品id、商品数量更新购物车 */
    public boolean updateOne(Long userId,Long productId,Long quantity){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<Cart> c = cartRepository.findByUserIdAndProductId(userId,productId);
        //该商品在原购物车中存在
        if(c.isPresent()) {
            Cart cart = c.get();
            //更新商品数量、更新时间即可
            cart.setQuantity(quantity);
            cart.setUpdateTime(now);
            cartRepository.save(cart);
        }
        //该商品在原购物车中不存在
        else{
            Product product = productRepository.findById(productId).get();
            cartRepository.save(new Cart(userId,productId,product.getName(),product.getPictureUrl(),
                    product.getPrice(),product.getPrice(),product.getPrice(),quantity,
                    product.getStatus(),now,now,0,0,0,0));
        }
        return true;
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

    /**购物车商品降价10%提醒*/
    public boolean priceDrop10Reminder(Cart cart){
        //该用户购物车中该商品未收到过降价10%通知
        if(cart.getNotifyTen() == 0) {
            double oldPrice = Double.parseDouble(cart.getOldPrice().toString());
            double currentPrice = Double.parseDouble(cart.getCurrentPrice().toString());
            double priceDifference = (oldPrice - currentPrice )/ oldPrice;
            //商品降价幅度超过10%
            if (priceDifference > 0.1) {
                int days = timeDifference(cart.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                //用户购物车中加入该商品时间小于10天
                if (days < 10) {
//                    System.out.println("用户id：" + cart.getUserId() + " 商品id： " + cart.getProductId() + "降价10%提醒！");

                    //将消息通知保存到数据库
                    String message = "尊敬的用户你好:  你收藏的商品：  " + cart.getProductName() + "  加入收藏夹以来降价幅度超过10%，快来关注一下吧！！";
                    Timestamp createTime = new Timestamp(System.currentTimeMillis());
                    noticeRepository.save(new Notice(cart.getUserId(), message,cart.getProductId(),cart.getProductName(),cart.getProductPicture(),
                            cart.getOldPrice(),cart.getCurrentPrice(), BigDecimal.valueOf(priceDifference),createTime,0));

                    //更新购物车中该商品降价10%通知状态为：已通知
                    cart.setNotifyTen(1);
                    cartRepository.save(cart);
                    return true;
                }
            }
        }
        return false;
    }

    /**购物车商品降价20%提醒*/
    public boolean priceDrop20Reminder(Cart cart){
        //该用户购物车中该商品未收到过降价20%通知
        if(cart.getNotifyTwenty() == 0) {
            double oldPrice = Double.parseDouble(cart.getOldPrice().toString());
            double currentPrice = Double.parseDouble(cart.getCurrentPrice().toString());
            double priceDifference = (oldPrice - currentPrice )/ oldPrice;
            //商品降价幅度超过20%
            if (priceDifference > 0.2) {
                int days = timeDifference(cart.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                //用户购物车中加入该商品时间小于20天
                if (days < 20) {
//                    System.out.println("用户id：" + cart.getUserId() + " 商品id： " + cart.getProductId() + "降价20%提醒！");

                    //将消息通知保存到数据库
                    String message = "尊敬的用户你好:  你收藏的商品：  " + cart.getProductName() + "  加入收藏夹以来降价幅度超过20%，快来关注一下吧！！";
                    Timestamp createTime = new Timestamp(System.currentTimeMillis());
                    noticeRepository.save(new Notice(cart.getUserId(), message,cart.getProductId(),cart.getProductName(),cart.getProductPicture(),
                            cart.getOldPrice(),cart.getCurrentPrice(), BigDecimal.valueOf(priceDifference),createTime,0));

                    //更新购物车中该商品降价20%通知状态为：已通知
                    cart.setNotifyTwenty(1);
                    cart.setNotifyTen(1);
                    cartRepository.save(cart);
                    return true;
                }
            }
        }
        return false;
    }

    /**购物车商品降价30%提醒*/
    public boolean priceDrop30Reminder(Cart cart){
        //该用户购物车中该商品未收到过降价30%通知
        if(cart.getNotifyThirty() == 0) {
            double oldPrice = Double.parseDouble(cart.getOldPrice().toString());
            double currentPrice = Double.parseDouble(cart.getCurrentPrice().toString());
            double priceDifference = (oldPrice - currentPrice )/ oldPrice;
            //商品降价幅度超过30%
            if (priceDifference > 0.3) {
                int days = timeDifference(cart.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                //用户购物车中加入该商品时间小于30天
                if (days < 30) {
//                    System.out.println("用户id：" + cart.getUserId() + " 商品id： " + cart.getProductId() + "降价30%提醒！");

                    //将消息通知保存到数据库
                    String message = "尊敬的用户你好:  你收藏的商品：  " + cart.getProductName() + "  加入收藏夹以来降价幅度超过30%，快来关注一下吧！！";
                    Timestamp createTime = new Timestamp(System.currentTimeMillis());
                    noticeRepository.save(new Notice(cart.getUserId(), message,cart.getProductId(),cart.getProductName(),cart.getProductPicture(),
                            cart.getOldPrice(),cart.getCurrentPrice(), BigDecimal.valueOf(priceDifference),createTime,0));

                    //更新购物车中该商品降价30%通知状态为：已通知
                    cart.setNotifyThirty(1);
                    cart.setNotifyTwenty(1);
                    cart.setNotifyTen(1);
                    cartRepository.save(cart);
                    return true;
                }
            }
        }
        return false;
    }

    /**购物车商品最低价格提醒*/
    public boolean priceLowestReminder(Cart cart){

        //该用户购物车中该商品未收到过最低价格通知
        if(cart.getNotifyLowest() == 0) {
            double oldPrice = Double.parseDouble(cart.getOldPrice().toString());
            double currentPrice = Double.parseDouble(cart.getCurrentPrice().toString());
            double lowestPrice = Double.parseDouble(cart.getLowestPrice().toString());
            double priceDifference = (oldPrice - currentPrice )/ oldPrice;
            //商品目前价格为加入购物车最低价格
            if (currentPrice < oldPrice && currentPrice == lowestPrice) {
                int days = timeDifference(cart.getCreateTime(), new Timestamp(System.currentTimeMillis()));
                //用户将该商品加入购物车时间小于30天
                if (days < 30) {
//                    System.out.println("用户id：" + cart.getUserId() + " 商品id： " + cart.getProductId() + "最低价格提醒！");

                    //将消息通知保存到数据库
                    String message = "尊敬的用户你好:  你收藏的商品：  " + cart.getProductName() + "  目前达到加入收藏夹以来最低价格，快来关注一下吧！！";
                    Timestamp createTime = new Timestamp(System.currentTimeMillis());
                    noticeRepository.save(new Notice(cart.getUserId(), message,cart.getProductId(),cart.getProductName(),cart.getProductPicture(),
                            cart.getOldPrice(),cart.getCurrentPrice(), BigDecimal.valueOf(priceDifference),createTime,0));

                    //更新购物车中该商品最低价格通知状态为：已通知
                    cart.setNotifyLowest(1);
                    cartRepository.save(cart);
                    return true;
                }
            }
        }
        return false;
    }
}
