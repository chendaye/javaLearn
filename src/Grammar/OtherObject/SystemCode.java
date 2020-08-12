package Grammar.OtherObject;

import java.util.Properties;

class SystemCode
{
    public static void main(String[] args)
    {

        // todo:获取系统信息
        Properties prop = System.getProperties();

        System.setProperty("mykey","myvalue");

        String value = System.getProperty("os.name");

        System.out.println("value="+value);
        String v = System.getProperty("haha");

        System.out.println("v="+v);


		/*
		for(Object obj : prop.keySet())
		{
			String value = (String)prop.get(obj);

			System.out.println(obj+"::"+value);
		}
		*/
    }
}
