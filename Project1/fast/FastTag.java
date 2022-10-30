package fast;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
public class FastTag
{
	static String add[]=new String[50];
    static int constant[]=new int[50];
    String veh_ft;
    Details ref=new Details();
    Scanner sc2=new Scanner(System.in);
    Date date=new Date();
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timef = new SimpleDateFormat("kk:mm");
    public void intialize()
    {
	for(int i=0;i<15;i++)
	{
	   constant[i]=500;
	}
    }
    public void Fast()
    {
        int fl=0;
        System.out.print("Enter the vehicle number: ");
        veh_ft=sc2.next();
	add[0]=veh_ft;
	String d= datef.format(date);
	String t= timef.format(date);
	add[1]=d;
	add[2]=t;
	int i=0;
        for(i=0;i<15;i++)
        {
            if((ref.Fvehno[i]).equals(veh_ft))
            {
		add[3]=ref.Fname[i];
		add[4]=ref.Fphno[i];
		add[5]=ref.Fvehtype[i];
		printing(add);
                System.out.println("           WAY : FASTTAG");
	    	int amt=amount(ref.Fvehtype[i]);
	    	constant[i]-=amt;
	    	System.out.println("BALANCE AMOUNT : "+constant[i]);
		if(constant[i]<amt)
		{
			System.out.println("YOUR BALANCE IS NOT SUFFICIENT FOR NEXT JOURNEY..\n  SO PLEASE RECHARGE YOUR ACCOUNT");
			constant[i]+=500;
		}
		System.out.println("\n**************************");
		fl=1;
		break;
            }
        }
	if(fl==0)
	{
	   System.out.println("    YOU HAVEN'T REGISTERED FOR FASTTAG!!    \n");
	}
    }
     public static int amount(String given)
    {
        int res=0;
        if(given.equals("Two wheeler"))
            res=20;
        else if(given.equals("Car"))
            res=40;
        else if(given.equals("Bus")||given.equals("Three wheeled vehicle"))
            res=100;
        else if(given.equals("LCV"))
            res=65;
        else if(given.equals("Axle Vehicle"))
            res=140;
        else if(given.equals("Commercial Vehicle"))
            res=170;
        else if(given.equals("Over sized Vehile"))
            res=230;
	System.out.println("        AMOUNT : "+res);
        return res;
        
    }
	public static void printing(String[] ptr)
	{
		System.out.println("\n**************************");
		System.out.println("National Highway Authority of India\nWelcome to IVRCL -Chengapalli Tollways Ltd");
		System.out.println("           Receipt for Toll                   ");
		System.out.println("   VEHICLE NO  :  "+ptr[0]);
		System.out.println("         DATE  :  "+ptr[1]);
		System.out.println("         TIME  :  "+ptr[2]);
		System.out.println("         NAME  :  "+ptr[3]);
		System.out.println("  PHONE NUMBER :  "+ptr[4]);
		System.out.println("  VEHICLE TYPE :  "+ptr[5]);
	}
    
}
class Details
{
    String Fvehno[]={"TN56UJ0255","TN33BV0039","TN33BF6543","TN33BH2842","TN33AJ1610","TN33AH1620",
    "TN37TH1722","TN33AH0478","TN56EY0676","TN33BH7989","TN24AB1234","TN33AG1612",
        "TN33AM1812","TN33AT2018","TN33AO5010"};
    String Fname[]={"Janu Pritha","Keerthi","Bavasri","Kapil","Jainth","Mohan","Gopika","Josika","Logesh","Kishore",
        "Logitha","Harnisha","MahinVishu","Kirthick","Dinesh"};
    String Fphno[]={"9865730223","9865730221","9865730222","9865730224","9865730225","9865730226","9865730227",
    "9865730228","9865730229","9865730220","9865730212","9865730213","9865730214","9865730215","9865730216"};
    String Fvehtype[]={"Over sized vehicle","Axle Vehicle","Commercial Vehicle","Two wheeler","Car","Over sized vehicle","Car","Bus","Car","Car","Bus","Bus","Car","LCV","Three wheeled vehicle"};
}