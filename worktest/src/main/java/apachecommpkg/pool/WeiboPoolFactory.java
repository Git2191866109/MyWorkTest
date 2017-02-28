package apachecommpkg.pool;

import com.mw.java.test.domain.Weibo;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by wei.ma on 2017/2/28.
 */

/**
 * 功能描述：激活资源对象
 * <p/>
 * 什么时候会调用此方法
 * 1：从资源池中获取资源的时候
 * 2：资源回收线程，回收资源的时候，根据配置的 testWhileIdle 参数，判断 是否执行 factory.activateObject()方法，true 执行，false 不执行
 */
public class WeiboPoolFactory implements PooledObjectFactory<Weibo> {


    @Override
    public PooledObject<Weibo> makeObject() throws Exception {
        System.out.println("make Object");
        Weibo Weibo = new Weibo(26, "178cm");
        return new DefaultPooledObject<Weibo>(Weibo);
    }

    @Override
    public void destroyObject(PooledObject<Weibo> p) throws Exception {
        System.out.println("destroy Object");
        Weibo Weibo = p.getObject();
        Weibo = null;
    }

    /**
     * 功能描述：判断资源对象是否有效，有效返回 true，无效返回 false
     * <p/>
     * 什么时候会调用此方法
     * 1：从资源池中获取资源的时候，参数 testOnBorrow 或者 testOnCreate 中有一个 配置 为 true 时，则调用  factory.validateObject() 方法
     * 2：将资源返还给资源池的时候，参数 testOnReturn，配置为 true 时，调用此方法
     * 3：资源回收线程，回收资源的时候，参数 testWhileIdle，配置为 true 时，调用此方法
     */
    @Override
    public boolean validateObject(PooledObject<Weibo> p) {
        System.out.println("validate Object");
        return true;
    }

    @Override
    public void activateObject(PooledObject<Weibo> p) throws Exception {
        System.out.println("activate Object");
    }

    /**
     * 功能描述：钝化资源对象
     * <p/>
     * 什么时候会调用此方法
     * 1：将资源返还给资源池时，调用此方法。
     */
    @Override
    public void passivateObject(PooledObject<Weibo> p) throws Exception {
        System.out.println("passivate Object");
    }

    public static void main(String[] args) throws Exception {
        //工厂
        WeiboPoolFactory factory = new WeiboPoolFactory();
        //资源池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(2);
//        poolConfig.setMaxActive(20); // 能从池中借出的对象的最大数目
        poolConfig.setMaxIdle(5); // 池中可以空闲对象的最大数目
//        poolConfig.setMaxWait(100); // 对象池空时调用borrowObject方法，最多等待多少毫秒
        poolConfig.setTimeBetweenEvictionRunsMillis(6000);// 间隔每过多少毫秒进行一次后台对象清理的行动
        poolConfig.setNumTestsPerEvictionRun(-1);// －1表示清理时检查所有线程
        poolConfig.setMinEvictableIdleTimeMillis(3000);// 设定在进行后台对象清理时，休眠时间超过了3000毫秒的对象为过期
        //创建资源池
        GenericObjectPool<Weibo> objectPool = new GenericObjectPool<Weibo>(factory, poolConfig);

        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            //获取资源对象
            Weibo weibo = objectPool.borrowObject();
            //将获取的资源对象，返还给资源池
            objectPool.returnObject(weibo);
            //输出资源对象
            System.out.println(i + ".." + weibo);
            System.out.println();
        }
    }
}
//public class WeiboPoolFactory extends BasePooledObjectFactory<Weibo> {
//    @Override
//    public Weibo create() throws Exception {
//        return null;
//    }
//
//    @Override
//    public PooledObject<Weibo> wrap(Weibo obj) {
//        return null;
//    }
//}
