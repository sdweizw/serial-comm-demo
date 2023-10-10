# serial-comm-demo
本项目是Java串口通信的springboot-starter，可以轻易集成入springboot项目中，简单几步就可以方便的读写串口数据

**支持windows、linux环境**

## 使用方式：

### 1下载serial-comm-demo-0.0.1-SNAPSHOT.jar，添加到项目中，在项目pom.xml中添加配置
    <dependency>
        <groupId>org.vesalainen</groupId>
        <artifactId>comm</artifactId>
        <version>1.0.3</version>
    </dependency>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>serial-comm-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/..jar包路径../serial-comm-demo-0.0.1-SNAPSHOT.jar</systemPath>
    </dependency>

### 2.在配置文件如application.yml中添加串口配置，以下示意添加COM1、COM2两个串口，波特率均为9600
    com:
      serial:
        ports:
          - { port: COM1, speed: 9600 }
          - { port: COM2, speed: 9600 }

### 3.实现SerialPortReader接口读取串口数据，注册到spring容器中
    @Component
    public class MySerialPortReader implements SerialPortReader {
        @Override
        public void read(String port, String msg) {
            if ("COM1".equals(port)) {
                System.out.println(msg);
            } 
        }
    }

### 4.如果想向串口写数据，在业务类中注入SerialPortWriter
    
    @Autowired
    private SerialPortWriter serialPortWriter;
    
    /**
     * 业务方法
     */
    public void func() {
        /*业务代码*/
        serialPortWriter.write("COM2", "hello serial-comm-demo");
        /*业务代码*/
    }
