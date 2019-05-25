Spring Lifecycle
================

这是一个用来直观查看 Spring 启动过程的工程。
----------------

通过日志，直观的查看 Spring 启动过程中。不同接口的组件，何时被调用。通过附带的描述，了解组件为什么会被调用，由什么引入。结合代码，深入学习 Spring。

懒得跑代码的，这里直接贴日志。但是最好还是结合代码。

```
10:43:55.796 [main] INFO com.jonas.test.spring.lifecycle.Application - SpringApplication.run                                                                 | 开始

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)

2019-05-25 10:43:56.073  INFO 12308 --- [           main] c.j.test.spring.lifecycle.Application    : Starting Application on ......
2019-05-25 10:43:56.074  INFO 12308 --- [           main] c.j.test.spring.lifecycle.Application    : No active profile set, falling back to default profiles: default
2019-05-25 10:43:56.112  INFO 12308 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@15b3e5b: startup date [Sat May 25 10:43:56 CST 2019]; root of context hierarchy
2019-05-25 10:43:56.300  INFO 12308 --- [           main] c.o.t.MyImportBeanDefinitionRegistrar    : ImportBeanDefinitionRegistrar.registerBeanDefinitions                                 | 主 Context 启动时，先处理了 @Import。而且是registry，优先级确实高
2019-05-25 10:43:56.333  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : ApplicationContextAware.setApplicationContext                                         | setApplicationContext是在Spring构造一个bean时调用的。所以对于整体上下文过程，这一句实际不用关心先后。上下文对象信息是 org.springframework.context.annotation.AnnotationConfigApplicationContext 和 id application
2019-05-25 10:43:56.333  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry                 | 之后开始处理registry。这里处理，应该是来自主Context的@SpringBootApplication带的@ComponentScan。 这里registry类型是org.springframework.beans.factory.support.DefaultListableBeanFactory
2019-05-25 10:43:56.333  INFO 12308 --- [           main] .l.MyBeanDefinitionRegistryPostProcessor : BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry                 | 同上，registry的处理顺序，应该是要高于factory和processor等组件。也是主Context扫描而来
2019-05-25 10:43:56.334  INFO 12308 --- [           main] .t.s.l.MyConfigurationAwareConfiguration : (@Configuration).constructor                                                          | 开始处理@Configuration了？？？这个@Configuration也是由主Context扫描到的。参考下一行日志，这里开始构造，但是还没开始调用@Bean。应该也是在收集类型信息
2019-05-25 10:43:56.336  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第1次
2019-05-25 10:43:56.344  INFO 12308 --- [           main] .t.s.l.MyConfigurationAwareConfiguration : (@Configuration)(@Bean).myConfigurationAware                                          | 开始调用@Bean方法，这里取得了@EnableConfigurationProperties(MyConfigurationAwareProperties.class)的实例
2019-05-25 10:43:56.344  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyConfigurationAware   : ApplicationContextAware.setApplicationContext                                         | 这就没啥好说的了，参照上面的说明
2019-05-25 10:43:56.344  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyConfigurationAware   : BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry                 | 还是优先（尽快）处理registry。级别真的高。不过这里不是扫描到的，而是来自上面的@Configuration，MyConfigurationAwareConfiguration。说明会尽快收集类型信息，然后有些类型信息会尽早处理？？？还是得看看源码
2019-05-25 10:43:56.344  INFO 12308 --- [           main] com.other.test.MyImportAware             : MyImportAware.constructor                                                             | 开始构造MyImportAware了。
2019-05-25 10:43:56.344  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 这个类是通过主Context类上标记@EnableMyImportAware注解，然后这个注解再@Import这个类。
2019-05-25 10:43:56.345  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 说明开始处理这类自定义@Import注解了，不过顺序不好确定，有可能是因为这个注解的书写顺序是在@Import下面
2019-05-25 10:43:56.345  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 才会比上面的MyImportBeanDefinitionRegistrar晚。
2019-05-25 10:43:56.345  INFO 12308 --- [           main] com.other.test.MyImportAware             : ApplicationContextAware.setApplicationContext                                         | 依然是没啥好说的
2019-05-25 10:43:56.345  INFO 12308 --- [           main] com.other.test.MyImportAware             : ApplicationContextAware.getBean (MyImportProperties) (Before)                         | 
2019-05-25 10:43:56.345  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第2次
2019-05-25 10:43:56.346  INFO 12308 --- [           main] com.other.test.MyImportAware             : ApplicationContextAware.getBean (MyImportProperties) (After)                          | MyImportProperties{name='null'}
2019-05-25 10:43:56.346  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 获取出来的MyImportProperties实例是有的。但是实例的属性，name却是null。实际配置文件是有内容的。
2019-05-25 10:43:56.346  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 这说明过早的获取了properties。1.x的Spring是何时绑定配置文件到@ConfigurationProperties实例的？
2019-05-25 10:43:56.346  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 这也说明，在setApplicationContext里做一些逻辑处理，需要考虑更多。
2019-05-25 10:43:56.346  INFO 12308 --- [           main] com.other.test.MyImportAware             : .                                                                                     | 并且可以看到这里applicationContext.getBean扫描了一次类型信息，并且FactoryBean的类型信息并没有被缓存。因为getObjectType又被调用了。
2019-05-25 10:43:56.347  INFO 12308 --- [           main] com.other.test.MyImportAware             : BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry                 | 还是registry，不用多说
2019-05-25 10:43:56.347  INFO 12308 --- [           main] o.t.OtherConfigurationAwareConfiguration : .constructor                                                                          | 这是主Context类上标记@EnableMyImportAware注解@Import的另一个类
2019-05-25 10:43:56.347  INFO 12308 --- [           main] o.t.OtherConfigurationAwareConfiguration : (@Bean).otherConfigurationAware                                                       | Configuration中的这些@Bean，何时被实例化（也就是调用方法）可能还是看实例的类型信息，比如实现了这些BeanFactory或者Registry一类接口的，会被较早的初始化
2019-05-25 10:43:56.347  INFO 12308 --- [           main] o.t.OtherConfigurationAwareConfiguration : .                                                                                     | OtherConfigurationAware也实现了registry和factory一类的接口。但是，是哪里的代码促使这一行为，是一个很重要的问题。
2019-05-25 10:43:56.347  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : ApplicationContextAware.setApplicationContext                                         | 
2019-05-25 10:43:56.347  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : ApplicationContextAware.getBean (MyImportProperties) (Before)                         | 
2019-05-25 10:43:56.348  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第3次
2019-05-25 10:43:56.348  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : ApplicationContextAware.getBean (MyImportProperties) (After)                          | MyImportProperties{name='null'}
2019-05-25 10:43:56.348  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : .                                                                                     | 这里还是applicationContext.getBean(MyImportProperties.class)。get出来，属性还是null。
2019-05-25 10:43:56.348  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry                 | 
2019-05-25 10:43:56.348  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : .                                                                                     | 这里注意！下面一行日志会提示myConfigurationAwareConfiguration被过早的创建了。而且无论运行多少次，出现的位置相同。可能说明Spring对registry的处理要告一段落了。
2019-05-25 10:43:56.348  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : .                                                                                     | 可以看到，警告之后，就开始处理factory了。
2019-05-25 10:43:56.348  WARN 12308 --- [           main] o.s.c.a.ConfigurationClassPostProcessor  : Cannot enhance @Configuration bean definition 'myConfigurationAwareConfiguration' since its singleton instance has been created too early. The typical cause is a non-static @Bean method with a BeanDefinitionRegistryPostProcessor return type: Consider declaring such methods as 'static'.
2019-05-25 10:43:56.397  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : BeanFactoryPostProcessor.postProcessBeanFactory                                       | beanFactory 的类型是 class org.springframework.beans.factory.support.DefaultListableBeanFactory
2019-05-25 10:43:56.397  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : BeanFactoryPostProcessor.getBean (AutoAwareProperties) (Before)                       | 
2019-05-25 10:43:56.397  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第4次
2019-05-25 10:43:56.397  INFO 12308 --- [           main] c.j.t.s.lifecycle.AutoAwareProperties    : (@ConfigurationProperties).constructor                                                | 
2019-05-25 10:43:56.399  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : BeanFactoryPostProcessor.getBean (AutoAwareProperties) (After)                        | AutoAwareProperties{name='null'}
2019-05-25 10:43:56.399  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyAutoAware    : .                                                                                     | 这里是在postProcessBeanFactory方法里，get properties实例。可见还是太早。不绑定数据。
2019-05-25 10:43:56.399  INFO 12308 --- [           main] .l.MyBeanDefinitionRegistryPostProcessor : BeanFactoryPostProcessor.postProcessBeanFactory                                       | 注意，这里是来自BeanDefinitionRegistryPostProcessor继承的BeanFactoryPostProcessor
2019-05-25 10:43:56.399  INFO 12308 --- [           main] .l.MyBeanDefinitionRegistryPostProcessor : .                                                                                     | 后边就开始，按初始化的顺序，逐个处理postProcessBeanFactory。
2019-05-25 10:43:56.399  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyConfigurationAware   : BeanFactoryPostProcessor.postProcessBeanFactory                                       | 
2019-05-25 10:43:56.400  INFO 12308 --- [           main] com.other.test.MyImportAware             : BeanFactoryPostProcessor.postProcessBeanFactory                                       | 
2019-05-25 10:43:56.400  INFO 12308 --- [           main] com.other.test.OtherConfigurationAware   : BeanFactoryPostProcessor.postProcessBeanFactory                                       | 
2019-05-25 10:43:56.408  INFO 12308 --- [           main] c.j.t.s.l.MyBeanFactoryPostProcessor     : BeanFactoryPostProcessor.postProcessBeanFactory                                       | 
2019-05-25 10:43:56.425  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第5次
2019-05-25 10:43:56.425  INFO 12308 --- [           main] c.j.t.s.l.BeforeMyBeanPostProcessor      : .constructor                                                                          | 故意测试给BeanPostProcessor接口的实现类，注入一个组件会怎样。果然，下一句就会报警告。
2019-05-25 10:43:56.428  INFO 12308 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'beforeMyBeanPostProcessor' of type [com.jonas.test.spring.lifecycle.BeforeMyBeanPostProcessor] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2019-05-25 10:43:56.432  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.context.event.internalEventListenerProcessor
2019-05-25 10:43:56.432  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.context.event.internalEventListenerProcessor
2019-05-25 10:43:56.434  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.context.event.internalEventListenerFactory
2019-05-25 10:43:56.434  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.context.event.internalEventListenerFactory
2019-05-25 10:43:56.437  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第6次
2019-05-25 10:43:56.438  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第7次
2019-05-25 10:43:56.438  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第8次
2019-05-25 10:43:56.439  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第9次
2019-05-25 10:43:56.439  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyServiceProperties    : MyServiceProperties.constructor                                                       | 
2019-05-25 10:43:56.442  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第10次
2019-05-25 10:43:56.442  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第11次
2019-05-25 10:43:56.442  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第12次
2019-05-25 10:43:56.445  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=com.test.service-com.jonas.test.spring.lifecycle.MyServiceProperties
2019-05-25 10:43:56.445  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=com.test.service-com.jonas.test.spring.lifecycle.MyServiceProperties
2019-05-25 10:43:56.445  INFO 12308 --- [           main] c.jonas.test.spring.lifecycle.MyService  : .constructor                                                                          | MyServiceProperties{name='Service'}
2019-05-25 10:43:56.446  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myService
2019-05-25 10:43:56.447  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myService
2019-05-25 10:43:56.447  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=application
2019-05-25 10:43:56.447  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=application
2019-05-25 10:43:56.449  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myEventListener
2019-05-25 10:43:56.449  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myEventListener
2019-05-25 10:43:56.450  INFO 12308 --- [           main] s.l.GetPropertiesApplicationContextAware : ApplicationContextAware.setApplicationContext                                         | 
2019-05-25 10:43:56.450  INFO 12308 --- [           main] s.l.GetPropertiesApplicationContextAware : ApplicationContextAware.getBean (GetInContextAwareProperties) (Before)                | 
2019-05-25 10:43:56.451  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第13次
2019-05-25 10:43:56.451  INFO 12308 --- [           main] c.j.t.s.l.GetInContextAwareProperties    : .constructor                                                                          | 这里注意，这个类是被上面的Configuration启用的。但是因为没有显式注入，而是在setApplicationContext里getBean。所以初始化比上面的Configuration晚。在被用的时候才初始化。
2019-05-25 10:43:56.453  INFO 12308 --- [           main] c.j.t.s.l.GetInContextAwareProperties    : GetInContextAwareProperties.setName                                                   | 可以看到，这里setName被调用。这一阶段，@ConfigurationProperties的数据可以被绑定。
2019-05-25 10:43:56.453  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=com.test.context.aware-com.jonas.test.spring.lifecycle.GetInContextAwareProperties
2019-05-25 10:43:56.453  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=com.test.context.aware-com.jonas.test.spring.lifecycle.GetInContextAwareProperties
2019-05-25 10:43:56.453  INFO 12308 --- [           main] s.l.GetPropertiesApplicationContextAware : ApplicationContextAware.getBean (GetInContextAwareProperties) (After)                 | GetInContextAwareProperties{name='Context Aware'}
2019-05-25 10:43:56.454  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=getPropertiesApplicationContextAware
2019-05-25 10:43:56.454  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=getPropertiesApplicationContextAware
2019-05-25 10:43:56.455  INFO 12308 --- [           main] c.j.t.s.l.MyApplicationContextAware      : MyApplicationContextAware.setApplicationContext                                       | org.springframework.context.annotation.AnnotationConfigApplicationContext; application
2019-05-25 10:43:56.455  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myApplicationContextAware
2019-05-25 10:43:56.455  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myApplicationContextAware
2019-05-25 10:43:56.456  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanFactoryAware     : BeanFactoryAware.setBeanFactory                                                       | 
2019-05-25 10:43:56.456  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myBeanFactoryAware
2019-05-25 10:43:56.456  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myBeanFactoryAware
2019-05-25 10:43:56.457  INFO 12308 --- [           main] c.j.t.spring.lifecycle.MyBeanNameAware   : BeanNameAware.setBeanName                                                             | myBeanNameAware
2019-05-25 10:43:56.458  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myBeanNameAware
2019-05-25 10:43:56.458  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myBeanNameAware
2019-05-25 10:43:56.458  INFO 12308 --- [           main] c.j.t.spring.lifecycle.MyConfiguration   : .constructor                                                                          | 因为这个Configuration没有实现或者提供，任何特殊类型的实例，所以现在才初始化。
2019-05-25 10:43:56.459  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myConfiguration
2019-05-25 10:43:56.459  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myConfiguration
2019-05-25 10:43:56.461  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myFactoryBean
2019-05-25 10:43:56.461  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myFactoryBean
2019-05-25 10:43:56.461  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第14次
2019-05-25 10:43:56.462  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyProperties   : MyProperties.constructor                                                              | 
2019-05-25 10:43:56.463  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyProperties   : MyProperties.setName                                                                  | 
2019-05-25 10:43:56.463  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=com.test.my-com.jonas.test.spring.lifecycle.MyProperties
2019-05-25 10:43:56.463  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=com.test.my-com.jonas.test.spring.lifecycle.MyProperties
2019-05-25 10:43:56.471  INFO 12308 --- [           main] c.j.t.spring.lifecycle.MyConfiguration   : (@Bean).myConfigurationOutputBean                                                     | MyProperties{name='Prop'}
2019-05-25 10:43:56.471  INFO 12308 --- [           main] c.j.t.s.l.MyConfigurationOutputBean      : .constructor                                                                          | 
2019-05-25 10:43:56.473  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=myConfigurationOutputBean
2019-05-25 10:43:56.473  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=myConfigurationOutputBean
2019-05-25 10:43:56.474  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.AutoConfigurationPackages
2019-05-25 10:43:56.474  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.AutoConfigurationPackages
2019-05-25 10:43:56.478  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
2019-05-25 10:43:56.478  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration
2019-05-25 10:43:56.485  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
2019-05-25 10:43:56.486  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration
2019-05-25 10:43:56.486  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第15次
2019-05-25 10:43:56.490  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=objectNamingStrategy
2019-05-25 10:43:56.490  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=objectNamingStrategy
2019-05-25 10:43:56.497  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=mbeanServer
2019-05-25 10:43:56.497  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=mbeanServer
2019-05-25 10:43:56.503  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=mbeanExporter
2019-05-25 10:43:56.503  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=mbeanExporter
2019-05-25 10:43:56.504  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第16次
2019-05-25 10:43:56.505  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第17次
2019-05-25 10:43:56.507  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
2019-05-25 10:43:56.508  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration
2019-05-25 10:43:56.513  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=springApplicationAdminRegistrar
2019-05-25 10:43:56.513  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=springApplicationAdminRegistrar
2019-05-25 10:43:56.516  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
2019-05-25 10:43:56.516  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
2019-05-25 10:43:56.516  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第18次
2019-05-25 10:43:56.519  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
2019-05-25 10:43:56.519  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties
2019-05-25 10:43:56.522  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
2019-05-25 10:43:56.522  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration
2019-05-25 10:43:56.524  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessBeforeInitialization                                     | beanName=org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration
2019-05-25 10:43:56.524  INFO 12308 --- [           main] c.j.t.s.lifecycle.MyBeanPostProcessor    : BeanPostProcessor.postProcessAfterInitialization                                      | beanName=org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration
2019-05-25 10:43:56.524  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第19次
2019-05-25 10:43:56.525  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第20次
2019-05-25 10:43:56.532  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第21次
2019-05-25 10:43:56.542  INFO 12308 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-05-25 10:43:56.543  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第22次
2019-05-25 10:43:56.544  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第23次
2019-05-25 10:43:56.545  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.isSingleton                                                               | 
2019-05-25 10:43:56.546  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第24次
2019-05-25 10:43:56.547  INFO 12308 --- [           main] c.j.t.s.lifecycle.event.MyEventListener  : (@EventListener).handleContextRefreshedEvent                                          | 
2019-05-25 10:43:56.556  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第25次
2019-05-25 10:43:56.557  INFO 12308 --- [           main] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第26次
2019-05-25 10:43:56.557  INFO 12308 --- [           main] c.j.test.spring.lifecycle.Application    : Application (CommandLineRunner).run                                                   | 
<====
null
org.springframework.beans.factory.support.DefaultListableBeanFactory@1f0f1111: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,application,org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory,beforeMyBeanPostProcessor,myEventListener,getPropertiesApplicationContextAware,myApplicationContextAware,myAutoAware,myBeanDefinitionRegistryPostProcessor,myBeanFactoryAware,myBeanFactoryPostProcessor,myBeanNameAware,myBeanPostProcessor,myConfiguration,myConfigurationAwareConfiguration,myFactoryBean,myService,com.test.context.aware-com.jonas.test.spring.lifecycle.GetInContextAwareProperties,org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor,org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor.store,com.test.auto-com.jonas.test.spring.lifecycle.AutoAwareProperties,myConfigurationOutputBean,com.test.my-com.jonas.test.spring.lifecycle.MyProperties,myConfigurationAware,com.test.aware.config-com.jonas.test.spring.lifecycle.MyConfigurationAwareProperties,com.test.service-com.jonas.test.spring.lifecycle.MyServiceProperties,com.other.test.MyImportAware,com.other.test.OtherConfigurationAwareConfiguration,otherConfigurationAware,org.springframework.boot.autoconfigure.AutoConfigurationPackages,com.test.import-com.jonas.test.spring.lifecycle.MyImportProperties,org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,org.springframework.boot.autoconfigure.condition.BeanTypeRegistry,propertySourcesPlaceholderConfigurer,org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,mbeanExporter,objectNamingStrategy,mbeanServer,org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,springApplicationAdminRegistrar,org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,spring.info-org.springframework.boot.autoconfigure.info.ProjectInfoProperties,org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration]; root of factory hierarchy
com.jonas.test.spring.lifecycle.MyService@1be2019a
<====
2019-05-25 10:43:56.558  INFO 12308 --- [           main] c.j.test.spring.lifecycle.Application    : Started Application in 0.671 seconds (JVM running for 2.2)
2019-05-25 10:43:56.558  INFO 12308 --- [           main] c.j.test.spring.lifecycle.Application    : SpringApplication.run                                                                 | 结束
2019-05-25 10:43:56.559  INFO 12308 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@15b3e5b: startup date [Sat May 25 10:43:56 CST 2019]; root of context hierarchy
2019-05-25 10:43:56.560  INFO 12308 --- [       Thread-2] c.j.t.s.lifecycle.event.MyEventListener  : (@EventListener).handleContextClosedEvent                                             | 
2019-05-25 10:43:56.560  INFO 12308 --- [       Thread-2] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.isSingleton                                                               | 
2019-05-25 10:43:56.560  INFO 12308 --- [       Thread-2] c.j.test.spring.lifecycle.MyFactoryBean  : FactoryBean.getObjectType                                                             | 也是由主Context扫描到的。应该是先收集类型信息。不过会调用多次。这是第27次
2019-05-25 10:43:56.561  INFO 12308 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown

Process finished with exit code 0

```

## The life of a bean

### 一个 Bean 的一生

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)

2019-05-25 10:59:36.229  INFO 8964 --- [           main] c.j.t.s.o.TheLifeOfABeanApplication      : Starting TheLifeOfABeanApplication on ......
2019-05-25 10:59:36.237  INFO 8964 --- [           main] c.j.t.s.o.TheLifeOfABeanApplication      : No active profile set, falling back to default profiles: default
2019-05-25 10:59:36.290  INFO 8964 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@6d3af739: startup date [Sat May 25 10:59:36 CST 2019]; root of context hierarchy
2019-05-25 10:59:36.694  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : .constructor                                                                          | 
2019-05-25 10:59:36.697  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : ApplicationContextAware.setBeanName                                                   | 
2019-05-25 10:59:36.697  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : BeanNameAware.setBeanFactory                                                          | 
2019-05-25 10:59:36.697  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : BeanFactoryAware.setApplicationContext                                                | 
2019-05-25 10:59:36.698  INFO 8964 --- [           main] c.j.t.spring.onebean.ABeanPostProcessor  : BeanPostProcessor.postProcessBeforeInitialization                                     | 
2019-05-25 10:59:36.699  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : .@PostConstruct                                                                       | 
2019-05-25 10:59:36.700  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : InitializingBean.afterPropertiesSet                                                   | 
2019-05-25 10:59:36.701  INFO 8964 --- [           main] com.jonas.test.spring.onebean.TheBean    : (@Bean initMethod=).init                                                              | 
2019-05-25 10:59:36.701  INFO 8964 --- [           main] c.j.t.spring.onebean.ABeanPostProcessor  : BeanPostProcessor.postProcessAfterInitialization                                      | 
2019-05-25 10:59:36.774  INFO 8964 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-05-25 10:59:36.781  INFO 8964 --- [           main] c.j.t.s.o.TheLifeOfABeanApplication      : Started TheLifeOfABeanApplication in 0.765 seconds (JVM running for 2.723)
2019-05-25 10:59:36.782  INFO 8964 --- [       Thread-2] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@6d3af739: startup date [Sat May 25 10:59:36 CST 2019]; root of context hierarchy
2019-05-25 10:59:36.783  INFO 8964 --- [       Thread-2] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2019-05-25 10:59:36.783  INFO 8964 --- [       Thread-2] com.jonas.test.spring.onebean.TheBean    : .@PreDestroy                                                                          | 
2019-05-25 10:59:36.783  INFO 8964 --- [       Thread-2] com.jonas.test.spring.onebean.TheBean    : (@Bean destroyMethod=).destroy                                                        | 

Process finished with exit code 0
```

*这个工程还在进行中，还未完工。敬请期待...*