package threadTest;

public class InstanceTest {
    private static volatile InstanceTest instanceTest = null;

    public InstanceTest() {
    }

    public static InstanceTest getInstanceTest(){
        if(instanceTest==null){
            synchronized (InstanceTest.class){
                if(instanceTest==null){
                    instanceTest = new InstanceTest();
                }
            }
        }
        return instanceTest;
    }
}
