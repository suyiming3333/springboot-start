package com.corn.springboot.start.spring.beanlifecircle;

import com.corn.springboot.start.spring.beanlifecircle.annotation.MyAutowried;
import com.corn.springboot.start.spring.beanlifecircle.component.MySmartInitializingSingleton;
import com.corn.springboot.start.spring.beanlifecircle.entity.AwareBean;
import com.corn.springboot.start.spring.beanlifecircle.entity.Car;
import com.corn.springboot.start.spring.beanlifecircle.entity.User;
import com.corn.springboot.start.spring.beanlifecircle.service.ServiceOne;
import com.corn.springboot.start.spring.beanlifecircle.service.ServiceTwo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TestBeanLifeCircle
 * @Package com.corn.springboot.start.spring.beanlifecircle
 * @Description: TODO
 * @date 2020/11/12 9:39
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBeanLifeCircle {

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void registerBeanDefinition() {

    }

    /***
     * 一、BeanDefinitionBuilder:构建/配置 beanDefinition
     */
    @Test
    public void createBeanDefinition() {

        //根据类型创建builder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        //设置属性
        beanDefinitionBuilder.addPropertyValue("name", "audi");
        //创建bean定义
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("car", beanDefinition);

        Car car = (Car) beanFactory.getBean("car");

//        Car car = (Car) applicationContext.getBean("car");
//        User user = (User) applicationContext.getBean("user");
//        System.out.println(user.getCar().getName());
//        System.out.println(car.getName());

    }

    /**
     * 二、将xml/properties/注解等定义bean配置的文件解析为beandefinition
     */
    @Test
    public void beanDefinitionParsing() {
        //spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //注解方式定义bean的文件读取器
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory);

        //解析文件上的bean配置信息
        annotatedBeanDefinitionReader.register(ServiceOne.class, ServiceTwo.class);

        factory.getBeansOfType(BeanPostProcessor.class).values().forEach(factory::addBeanPostProcessor); // @1


        //打印出注册的bean的配置信息
        for (String beanName : new String[]{"serviceOne", "serviceTwo"}) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }


    /***
     * 三、beanDefinition注册、alias注册
     */
    @Test
    public void beanRegister() {
        //创建一个bean工厂，这个默认实现了BeanDefinitionRegistry接口，所以也是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        //定义一个bean
        GenericBeanDefinition nameBdf = new GenericBeanDefinition();
        nameBdf.setBeanClass(String.class);
        nameBdf.getConstructorArgumentValues().addIndexedArgumentValue(0, "xxx");

        //将bean注册到容器中
        factory.registerBeanDefinition("name", nameBdf);

        //通过名称获取BeanDefinition
        System.out.println(factory.getBeanDefinition("name"));
        //通过名称判断是否注册过BeanDefinition
        System.out.println(factory.containsBeanDefinition("name"));
        //获取所有注册的名称
        System.out.println(Arrays.asList(factory.getBeanDefinitionNames()));
        //获取已注册的BeanDefinition的数量
        System.out.println(factory.getBeanDefinitionCount());
        //判断指定的name是否使用过
        System.out.println(factory.isBeanNameInUse("name"));

        //别名相关方法
        //为name注册2个别名
        factory.registerAlias("name", "alias-name-1");
        factory.registerAlias("name", "alias-name-2");

        //判断alias-name-1是否已被作为别名使用
        System.out.println(factory.isAlias("alias-name-1"));

        //通过名称获取对应的所有别名
        System.out.println(Arrays.asList(factory.getAliases("name")));

        //最后我们再来获取一下这个bean
        System.out.println(factory.getBean("name"));

    }

    /**
     * 四、bean definition merge
     * bean定义可能存在多级父子关系，
     * 合并的时候进进行递归合并，最终
     * 得到一个包含完整信息的RootBeanDefinition
     */
    @Test
    public void mergeBeanDefinition() {

        //创建bean容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //创建一个bean xml解析器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(factory);
        //解析bean xml，将解析过程中产生的BeanDefinition注册到DefaultListableBeanFactory中
        beanDefinitionReader.loadBeanDefinitions("spring-application.xml");
        //遍历容器中注册的所有bean信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            //通过bean名称获取原始的注册的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取合并之后的BeanDefinition信息
            BeanDefinition mergedBeanDefinition = factory.getMergedBeanDefinition(beanName);

            System.out.println(beanName);
            System.out.println("解析xml过程中注册的beanDefinition：" + beanDefinition);
            System.out.println("beanDefinition中的属性信息" + beanDefinition.getPropertyValues());
            System.out.println("合并之后得到的mergedBeanDefinition：" + mergedBeanDefinition);
            System.out.println("mergedBeanDefinition中的属性信息" + mergedBeanDefinition.getPropertyValues());
            System.out.println("---------------------------");
        }

    }

    /**
     * 五、beanclass加载
     *  1、 AbstractBeanDefinition的setBeanClassName(String className)#指定带初始化的类名称
     *  2、AbstractBeanDefinition的Class<?> resolveBeanClass，classforname 实例化bean
     */

    /***
     * 六、bean实例化
     *
     */
    @Test
    public void beforeInitialnize() {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        /**
         * 1、bean实例化前
         *  bean容器添加 bean后置处理器。实例化前会调用AbstractAutowireCapableBeanFactory##applyBeanPostProcessorsBeforeInstantiation,
         *  执行所有的BeanPostProcessor
         */
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Nullable
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                System.out.println("调用postProcessBeforeInstantiation()");
                //发现类型是Car类型的时候，硬编码创建一个Car对象返回
                if (beanClass == Car.class) {
                    Car car = new Car();
                    car.setName("保时捷");
                    //直接return 不往下走bean实例化的步骤
                    return car;
                }
                return null;
            }
        });


        //定义一个car bean,车名为：奥迪
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "奥迪").  //@2
                getBeanDefinition();
        factory.registerBeanDefinition("car", carBeanDefinition);
        //从容器中获取car这个bean的实例，输出
        System.out.println(factory.getBean("car"));
    }

    /**
     * 2、bean实例化
     *  通过SmartInstantiationAwareBeanPostProcessor##determineCandidateConstructors
     *  采用指定构造方法实例化bean
     */

    @Test
    public void testSmartInstantiationAwareBeanPostProcessor(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        //添加自定义SmartInstantiationAwareBeanPostProcessor
        factory.addBeanPostProcessor(new SmartInstantiationAwareBeanPostProcessor(){
            @Nullable
            @Override
            public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
                System.out.println(beanClass);
                System.out.println("调用 MySmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors 方法");
                Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
                if (declaredConstructors != null) {
                    //获取有@MyAutowried注解的构造器列表
                    List<Constructor<?>> collect = Arrays.stream(declaredConstructors).
                            filter(constructor -> constructor.isAnnotationPresent(MyAutowried.class)).
                            collect(Collectors.toList());
                    //使用被MyAutowried注解的构造器
                    Constructor[] constructors = collect.toArray(new Constructor[collect.size()]);
                    return constructors.length != 0 ? constructors : null;
                } else {
                    return null;
                }
            }
        });

        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(User.class).getBeanDefinition();

        ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
        constructorArgumentValues.addGenericArgumentValue("su");
        constructorArgumentValues.addGenericArgumentValue(new Car("bmw"));

        factory.registerBeanDefinition("user",beanDefinition);

//        factory.registerBeanDefinition("user",
//                BeanDefinitionBuilder.
//                        genericBeanDefinition(User.class).
//                        addConstructorArgValue(new Car("bmw")).
//                        getBeanDefinition());

        User user = factory.getBean("user", User.class);
        System.out.println(user);

    }


    /**
     * 7、MergeBeanDefinition 处理
     * 调用AbstractAutowireCapableBeanFactory##applyMergedBeanDefinitionPostProcessors
     * 遍历所有的MergedBeanDefinitionPostProcessor
     *
     * spring实现类
     * AutowiredAnnotationBeanPostProcessor##postProcessMergedBeanDefinition
     *
     *  @Override
     * 	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
     * 	    //缓存方法 字段
     * 		InjectionMetadata metadata = findAutowiringMetadata(beanName, beanType, null);
     * 		metadata.checkConfigMembers(beanDefinition);
     * 	}
     *
     */


    /**
     * 8、bean属性设置阶段：调用AbstractAutowireCapableBeanFactory##populateBean 填充属性
     *  - 实例化后：postProcessAfterInstantiation
     *  - 属性赋值前：postProcessProperties
     *  - bean get set 赋值
     */

    @Test
    public void afterInitilized(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor(){
            /**
             * 返回false 跳过属性设置
             * @param bean
             * @param beanName
             * @return
             * @throws BeansException
             */
            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                if ("car".equals(beanName)) {
                    return false;
                } else {
                    return true;
                }
            }

        });

        //定义一个car bean,车名为：奥迪
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "奥迪").  //@2
                getBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition("car", carBeanDefinition);
        //从容器中获取car这个bean的实例，输出
        System.out.println(defaultListableBeanFactory.getBean("car"));

    }

    @Test
    public void postProcessProperties(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor(){
            /**
             * 修改properties
             * @param pvs
             * @param bean
             * @param beanName
             * @return
             * @throws BeansException
             */
            @Nullable
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                if ("car".equals(beanName)) {
                    if (pvs == null) {
                        pvs = new MutablePropertyValues();
                    }
                    if (pvs instanceof MutablePropertyValues) {
                        MutablePropertyValues mpvs = (MutablePropertyValues) pvs;
                        //将姓名设置为：路人
                        mpvs.add("name", "edited carname");
                    }
                }
                return null;
            }
        });

        //定义一个car bean,车名为：奥迪
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "奥迪").  //@2
                getBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition("car", carBeanDefinition);
        //从容器中获取car这个bean的实例，输出
        System.out.println(defaultListableBeanFactory.getBean("car"));

    }

    /**
     * 9、bean初始化阶段
     * 调用AbstractAutowireCapableBeanFactory#initializeBean
     * - Bean Aware回调。其中，分别注入BeanName、BeanClassLoader、BeanFactory
     * 	private void invokeAwareMethods(String beanName, Object bean) {
     * 		if (bean instanceof Aware) {
     * 			if (bean instanceof BeanNameAware) {
     * 				((BeanNameAware) bean).setBeanName(beanName);
     *                        }
     * 			if (bean instanceof BeanClassLoaderAware) {
     * 				ClassLoader bcl = getBeanClassLoader();
     * 				if (bcl != null) {
     * 					((BeanClassLoaderAware) bean).setBeanClassLoader(bcl);
     *                }
     *            }
     * 			if (bean instanceof BeanFactoryAware) {
     * 				((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
     *            }* 		}
     *    }
     */

    @Test
    public void testBeanAware(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("awareBean", BeanDefinitionBuilder.genericBeanDefinition(AwareBean.class).getBeanDefinition());
        //调用getBean方法获取bean，将触发bean的初始化
        factory.getBean("awareBean");
    }

    /**
     * - Bean初始化前：调用AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization
     * 会调用BeanPostProcessor的postProcessBeforeInitialization方法
     *
     * BeanPostProcessor 重要实现类：ApplicationContextAwareProcessor
     *
     *
     */

    @Test
    public void testBeanAwareBeforeInitialization() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareBean.class);
        context.refresh();
    }

    /**
     * - bean初始化
     * 调用InitializingBean接口的afterPropertiesSet方法
     * 调用定义bean的时候指定的初始化方法。
     *
     */

    /**
     * bean初始化后
     * 调用AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization
     * 会调用BeanPostProcessor的postProcessAfterInitialization方法
     */

    /**
     * 所有单例bean初始化完成后阶段
     */
    @Test
    public void SmartInitializingSingletonTest(){
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
////        context.register(SmartInitializingSingletonTest.class);
//        System.out.println("开始启动容器!");
//        context.refresh();
//        System.out.println("容器启动完毕!");

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("abean", BeanDefinitionBuilder.genericBeanDefinition(AwareBean.class).getBeanDefinition());
        factory.registerBeanDefinition("mySmartInitializingSingleton", BeanDefinitionBuilder.genericBeanDefinition(MySmartInitializingSingleton.class).getBeanDefinition());
        System.out.println("准备触发所有单例bean初始化");
        //触发所有bean初始化，并且回调 SmartInitializingSingleton#afterSingletonsInstantiated 方法
        factory.preInstantiateSingletons();
    }

    /**
     * bean的使用：getBean
     */

    /**
     * bean销毁:
     * 会调用DestructionAwareBeanPostProcessor#postProcessBeforeDestruction
     */
}
