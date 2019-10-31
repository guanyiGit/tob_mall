package com.contaoller;

import com.entities.CardDO;
import com.entities.GoodsCategoryDO;
import com.feign.service.BudgetService;
import com.feign.service.CartService;
import com.github.pagehelper.PageInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pojo.CascadeGoodsCategoryDO;
import com.pojo.Product;
import com.service.ProductService;
import com.utils.R;
import com.utils.ReqPage.QReqPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 商品相关
 */
@RestController
@RequestMapping(value = "/pruduct")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private CartService cartService;

    @GetMapping("/test2")
    public R hystrix2(Integer id, String name) {
        return budgetService.budgetPublic(1);
    }

    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "hystrix3")
    public R hystrix(Integer id, String name) {
        if (id != null && 3 == id) {
            throw new RuntimeException();
        }
        return R.ok().put("id", id).put("name", name);
    }

    public R hystrix3(Integer id, String name) {
        return R.ok();
    }

    /**
     * 查列表
     *
     * @param reqPage 分页参数 keyWord：商品名称
     * @param cid     商品分类，可null
     * @param type    null->无条件
     *                1->按照上架时间 倒序
     *                2->按照销售量 倒序
     * @return
     */
    @GetMapping
    public R list(QReqPage reqPage, Integer cid, Integer type) {
        PageInfo<Product> list;
        try {
            list = productService.list(reqPage, cid, type);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn(e.toString());
            return R.error(e.getMessage());
        }
        return R.ok(list);
    }

    /**
     * 查询我的购物车商品数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/cart/account/{userId}")
    public R account(@PathVariable long userId) {
        return cartService.account(userId);
    }

    @GetMapping(value = "/budget/budgetPublic/{orgId}")
    public R budgetPublic(@PathVariable int orgId) {
        return budgetService.budgetPublic(orgId);
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @PostMapping
    public R sava(Product product) {
        try {
            if (productService.save(product)) {
                return R.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
        }
        return R.error();
    }

    @GetMapping(value = "/skuAttrs/{goodsId}")
    public R GoodsSkuAttrs(@PathVariable long goodsId) {
        return R.ok(productService.skuAttrs(goodsId));
    }

    /**
     * 查商品详情[新增浏览记录]
     *
     * @param skuId     商品skuid
     * @param isHistroy 是否留下历史记录，default->false
     * @param hisUserId 添加记录的用户id
     *                  isHistroy=>true 参数必传
     *                  isHistroy=>false 参数无效
     * @return
     */
    @GetMapping(value = "/details/{skuId}")
    public R details(@PathVariable long skuId,
                     @RequestParam(value = "isHistroy", required = false, defaultValue = "false") boolean isHistroy,
                     @RequestParam(value = "hisUserId", required = false) Long hisUserId) {
        try {
            if (isHistroy && hisUserId == null) {
                throw new RuntimeException("参数错误！");
            }
            return R.ok(productService.details(skuId, isHistroy, hisUserId));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn(e.toString());
        }
        return R.error();
    }

    /**
     * 查看商品列表
     *
     * @param level 所属级别
     * @return
     */
    @GetMapping(value = "/categorys")
    public R categorys(Integer level) {
        return R.ok(productService.categorys(level));
    }

    /**
     * 查看商品级别列表 级联
     *
     * @param pid 父级pid
     * @return
     */
    @PatchMapping(value = "/categorys")
    public R categorysPatch(Integer pid) {
        return R.ok(this.cascade(productService.findChilds(pid), 0));
    }

    /**
     * 查看我的浏览历史 商品
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/historys/{userId}")
    public R historys(@PathVariable Long userId) {
        return R.ok(productService.historys(userId));
    }

    /**
     * 添加购物车
     *
     * @param cardDO
     * @return
     */
    @PostMapping(value = "/card/add")
    public R add(@RequestBody CardDO cardDO) {
        return R.ok(cartService.add(cardDO));
    }


    /**
     * 级别筛选
     *
     * @param list
     * @param pid
     * @return
     */
    private List<CascadeGoodsCategoryDO> cascade(List<GoodsCategoryDO> list, Integer pid) {
        if (list != null && list.size() > 0 && pid != null) {
            return list.stream()
                    .filter(x -> x != null && x.getParentCid() != null && pid == x.getParentCid())
                    .map(x -> {
                        CascadeGoodsCategoryDO aDo = new CascadeGoodsCategoryDO();
                        aDo.setCid(x.getCid());
                        aDo.setCName(x.getCName());
                        aDo.setCreateTime(x.getCreateTime());
                        aDo.setHasLeaf(x.getHasLeaf());
                        aDo.setLev(x.getLev());
                        aDo.setParentCid(x.getParentCid());
                        aDo.setStatus(x.getStatus());
                        aDo.setUpdateTime(x.getUpdateTime());

                        List<CascadeGoodsCategoryDO> children = this.cascade(list, x.getCid());
                        if (children != null) {
                            aDo.setChildren(children);
                        }
                        return aDo;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 获取轮播图
     * @return
     */
    @GetMapping(value = "/carousel")
    public R carousel() {
        String url1 = "https://img11.360buyimg.com/da/jfs/t1/20196/11/5829/81854/5c421f80Ee7663d1b/273bfd14908493f1.jpg";
        String url2 = "https://img12.360buyimg.com/da/jfs/t1/14093/36/5685/91437/5c4153f0Eeb446c2c/ddd50fbf11d4e27c.jpg";
        String url3 = "https://img20.360buyimg.com/da/jfs/t1/29386/27/4694/115958/5c355417E803e9bae/f74c3e5c2274d418.jpg";
        String url4 = "https://img11.360buyimg.com/da/jfs/t1/20196/11/5829/81854/5c421f80Ee7663d1b/273bfd14908493f1.jpg";
        String url5 = "https://img11.360buyimg.com/da/jfs/t1/25320/4/5568/89610/5c3f313cEf10e0fc5/bf3be913149350dd.jpg";
        String url6 = "https://img14.360buyimg.com/da/jfs/t1/9282/22/13025/113907/5c3c6294E7115c29d/030c7d4b33dc0d1b.jpg";
        String url7 = "https://img11.360buyimg.com/da/jfs/t1/9240/31/13108/166701/5c3da31fEd5d4ba7b/de0c6f3e557b1297.jpg";
//        String[] urls = new String[]{url1, url2, url3, url4, url5, url6, url7};
        url1 = "/static/images/38.jpg";
        url2 = "/static/images/38.jpg";
        url3 = "/static/images/38.jpg";
        String[] urls = new String[]{url1, url2, url3};
        return R.ok(urls);
    }
}
