package test;

public class QuartzTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  // TODO Auto-generated method stub
        TestJob job = new TestJob();
        String job_name ="11";
        try {
            System.out.println("【系统启动】");
            QuartzManager.addJob(job_name,job,"0/5 * * * * ?");
            
            Thread.sleep(10000);
            System.out.println("【修改时间】");
            QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");
            Thread.sleep(20000);
            System.out.println("【移除定时】");
            QuartzManager.removeJob(job_name);
            Thread.sleep(10000);
            
            System.out.println("\n【添加定时任务】");
            QuartzManager.addJob(job_name,job,"0/5 * * * * ?");
            
        }  catch (Exception e) {
            e.printStackTrace();
        }


	}

}
