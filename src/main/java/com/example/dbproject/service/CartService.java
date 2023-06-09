package com.example.dbproject.service;

import com.example.dbproject.VO.CartVO;
import com.example.dbproject.entity.Cart;
import com.example.dbproject.mapper.CartMapper;
import com.example.dbproject.mapper.CustomerMapper;
import com.example.dbproject.mapper.OrderItemMapper;
import com.example.dbproject.service.ex.CartException;
import com.example.dbproject.service.ex.CustomerException;
import com.example.dbproject.service.ex.InsertException;
import com.example.dbproject.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
@Service
public class CartService {
    /** 购物车业务层依赖于持久层（购物车+商品）*/
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CustomerService customerService;
    /**
    将商品添加到购物车
    id 用户id
    pid 商品id
    amount 添加数量
     */
    //@Override
    public void addToCart(Integer id,Integer pid,Integer amount){
        Cart result=cartMapper.selectCartByIdAndPid(id,pid);
        if(result==null){//购物车中原来没有要添加到商品
            Integer next=customerMapper.selectById(id).getNext_group_num();
            Integer rows=cartMapper.insertCart(id, pid, amount,next);
            if(rows!=1){
                throw new InsertException("插入失败，发生异常!");
            }
        }else{//购物车中本来就有要添加到商品，只用更新数量amount

            Integer rows=cartMapper.updateAmount(result.getId(), result.getPid(), amount);
            if(rows!=1){
                throw new UpdateException("更新失败，发生异常!");
            }
        }
    }

    public void deleteCart(Integer cid){
        Cart result=cartMapper.selectByCid(cid);
        Integer rows=cartMapper.deleteCart(cid);
        if(rows!=1){
            throw new InsertException("删除失败，发生异常!");
        }
    }

    public List<CartVO> selectVOById(Integer id) {
        List<CartVO> data= cartMapper.selectVOById(id);
        return data;
    }

    public Integer addAmount(Integer cid,Integer id,Integer pid,Integer delt){
        Cart result=cartMapper.selectByCid(cid);
        if (result ==null){
            throw new CartException("数据不存在");
        }
        if (result.getId()!=id){
            throw  new CustomerException("非法访问数据");
        }
        Integer num=result.getAmount()+delt;
        Integer rows=cartMapper.updateAmount(id,pid,delt);
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车数据总量
        return num;
    }

    public List<CartVO> selectVOByCid(Integer id, Integer[] cid){
        List<CartVO> list=cartMapper.selectVOByCid(cid);
        Iterator<CartVO> it= list.iterator();
        while (it.hasNext()){
           CartVO cartVO= it.next();
           if(cartVO.getId()!=id){//当前数据不属于当前用户
               //移除数据
                list.remove(cartVO);
           }
        }
        return list;
    }


    public Integer cartCount(Integer id){
        Integer cnt = cartMapper.selectCartCount(id);
        return cnt;
    }

    public String checkoutCart(Integer id,String dest){
        Integer data=customerMapper.selectById(id).getNext_group_num();
        Integer sum=null;

        //System.out.println("sumjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj="+sum);

        try{
            sum=-cartMapper.getPrice(id,data);
            System.out.println("sum="+sum);
        }catch (Exception e){
            System.out.println("sum:"+sum);
            e.printStackTrace();
        }

        String msg = "success";
        try{
            customerService.updateAccount(id,sum);
        }catch (Exception e){
            msg = e.getMessage();
            System.out.println("service:"+e.getMessage());
        }

        if(msg.equals("success")){
            data++;
            customerMapper.updateNext(id,data);

            List<CartVO> result=cartMapper.selectCartVOById(id);
            for (CartVO cartVO : result) {
                Integer pid=cartVO.getPid();
                Integer amount=cartVO.getAmount();
                Integer row=orderItemMapper.insertOrder(id,pid,amount,dest,data);
                if(row!=1){
                    throw new InsertException("加入失败，发生异常！");
                }
            }

            Integer rows=cartMapper.deleteAllCart(id);
            if(rows==0){
                throw new InsertException("删除失败，发生异常!");
            }
        }

        return msg;
    }

}
