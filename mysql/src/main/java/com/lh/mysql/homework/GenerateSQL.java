package com.lh.mysql.homework;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class GenerateSQL {

    private static String path = "E:\\Program\\Learing\\JavaTraining\\Course";
    private static String filenameTemp;
    private static String[] uuidList = new String[1000000];

    public static void main(String[] args) throws IOException {
        uuidList = getUUID(1000000);
        generateUser();
        generateUserAddress();
        generateProductCompany();
        generateProduct();
        generateModel();
        generateSeller();
        generateOrder();
        generateOrderDetail();
    }

    private static void generateUser() throws IOException {
        createTxtFile("user");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(randomStr(4)).append(", 123456, ").append(getTel()).append(", 男, 2020").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateUserAddress() throws IOException {
        createTxtFile("user_address");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(getRoad()).append(", ").append(getTel()).append(", ").append(i).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateProductCompany() throws IOException {
        createTxtFile("product_company");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10 * 1000; i++) {
            str.append(randomStr(8)).append(", ").append(getRoad()).append(", ").append(getCode(6)).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateProduct() throws IOException {
        createTxtFile("product_info");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(randomStr(6)).append(", ").append(i % 100).append(", null, ").append(i % 1000).append(", ").append(timestamp).append(", ").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateModel() throws IOException {
        createTxtFile("product_model");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(randomStr(6)).append(", ").append(getCode(4)).append(", ").append(getPrice(4)).append(", ")
                    .append(getPrice(3)).append(", null, ").append(0).append(", ").append(getPrice(7)).append(", ").append(timestamp).append(", ").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateSeller() throws IOException {
        createTxtFile("seller_info");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(randomStr(10)).append(", ").append(randomStr(6)).append(", ").append(getPrice(6)).append(", ")
                    .append(", null, ").append(getRoad()).append(", ").append(timestamp).append(", ").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateOrder() throws IOException {
        createTxtFile("order_info");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(uuidList[(int) (Math.random()*(1000000 - 1))]).append(", ").append(getPrice(7)).append(", ").append(getRoad()).append(", ").append(getPrice(4))
                    .append(", ").append(getStatusCode(1, 2)).append(", ").append(timestamp).append(", ").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    private static void generateOrderDetail() throws IOException {
        createTxtFile("order_detail");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 1000 * 1000; i++) {
            str.append(uuidList[(int) (Math.random()*(1000000 - 1))]).append(", ").append(i % 1000).append(", ").append(getStatusCode(1, 3)).append(", ").append(timestamp).append(", ").append(timestamp).append("\r\n");
        }

        System.out.println(str);
        writeTxtFile(str.toString());

        System.out.println("END Write...");
    }

    /**
     * 返回字符串
     * @param length
     * @return
     */
    private static String randomStr(int length) {
        int number = 0;
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(62);
            stringBuilder.append(str.charAt(number));
        }

        return stringBuilder.toString();
    }

    /**
     * 返回Code
     * @param length
     * @return
     */
    private static String getCode(int length) {
        int number = 0;
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(36);
            stringBuilder.append(str.charAt(number));
        }

        return stringBuilder.toString();
    }

    /**
     * 返回价格
     * @param length
     * @return
     */
    private static String getPrice(int length) {
        int number = 0;
        String str = "123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(9);
            stringBuilder.append(str.charAt(number));
        }

        return stringBuilder.toString();
    }

    /**
     * 返回价格
     * @param length
     * @return
     */
    private static String getStatusCode(int length, int count) {
        int number = 0;
        String str = "012";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(count);
            stringBuilder.append(str.charAt(number));
        }

        return stringBuilder.toString();
    }

    /**
     * 返回手机号码
     */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    private static String getTel() {
        int index = getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    private static String[] road="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    /**
     * 返回地址
     * @return
     */
    private static String getRoad() {
        int index=getNum(0,road.length-1);
        String first=road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }

    private static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static String[] getUUID(int num){

        if( num <= 0)
            return null;

        String[] uuidArr = new String[num];

        for (int i = 0; i < uuidArr.length; i++) {
            uuidArr[i] = getUUID32();
        }

        return uuidArr;
    }

    public static String getUUID32(){

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();

    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static boolean createTxtFile(String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }

    /**
     * 写文件
     *
     * @param newStr
     *            新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
//        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(newStr);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

}