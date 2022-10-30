import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import fast.*;
interface intermulti 
{
    public int getmulti();
    public void getmulti(int a,int b);
}
class Main 
{

    public static void main(String arg[])
    {
	Scanner sc=new Scanner(System.in);
        Tollgate obj=new Tollgate();
        obj.select_way();
    }
    public static void VEHICLE_TYPE(int tno,int way)
    {
        switch(tno)
        {
            case 1:
                Two_wheeler two=new Two_wheeler(way);
                break;
            case 2:
                Car ca=new Car(way);
                break;
            case 3:
                Bus ba=new Bus(way);
                break;
            case 4:
                LCV la=new LCV(way);
                break;
            case 5:
                Axle_vehicle ax=new Axle_vehicle(way);
                break;
            case 6:
                Commercial_vehicle cv=new Commercial_vehicle(way);
                break;
            case 7:
                Over_sized_Vehicle ov=new Over_sized_Vehicle(way);
                break;
            default:
                System.out.println("Invalid vehicle type");
        }        
    }
}
class Tollgate
{
    Scanner sc=new Scanner(System.in);
    int way;
    static String veh_no[]=new String[25];
    static int veh_type[]=new int[25];
    static String name[]=new String[25];
    static String ph_no[]=new String[25];
    void select_way()
    {
        FastTag fast=new FastTag();
        Multitrip mt=new Multitrip();
	SingleWay sw1=new SingleWay();
	fast.intialize();
        while(true)
        {
        System.out.println("Enter the type of way you choice:\n     1.Single way\n     2.Multiple trips\n     3.Fast Tag\n     4.Exit");
        way=sc.nextInt();
        switch(way)
        {
            case 1:
                sw1.single(way);
                break;
            case 2:
                int flag=mt.getmulti();
                mt.getmulti(flag,way);
                break;
            case 3:
                fast.Fast();
                break;
            case 4:
                System.exit(0);
        }
    }
    }
}
class SingleWay extends Tollgate
{
    
    Main obm=new Main();
    static int i=0;
    int fl=0;
    String incoming[]=new String[50];
    String outgoing;
    Date date=new Date();
    String time[]=new String[50];
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String str[]=new String[50];
    SimpleDateFormat timef = new SimpleDateFormat("kk:mm");
    int val;
    String d;
    int visited=0,dateextra=0;
    Print bl=new Print();
    public void single(int way)
    {
	fl=0;
	ArrayList<String> al=new ArrayList<String>();
        System.out.print("Enter the vehicle number:  ");
        veh_no[i]=sc.next();
	al.add(veh_no[i]);
	int j=0;
	visited=0;
	dateextra=0;
	for(j=0;j<i;j++)
	{
		val=j;
		if(veh_no[j].equals(veh_no[i]))
		{
			System.out.print("Enter date(dd/mm/yyyy): ");
			d=sc.next();
			if(d.equals(str[j])==true)
			{
				String t=timef.format(new Date());
				al.add(d);
				al.add(t);
				al.add(name[val]);
				al.add(ph_no[val]);
				bl.printing(al,4,veh_type[val]);
				fl=1;
			}
			else
			{
                		if((str[j].charAt(1))==d.charAt(1)-1)
				{
					System.out.print("Enter time: ");
					outgoing=sc.next();
					if(outgoing.charAt(0)==time[j].charAt(0) && outgoing.charAt(1)<time[j].charAt(1)||outgoing.charAt(0)<time[j].charAt(0))
					{
						al.add(d);
						al.add(outgoing);
						al.add(name[val]);
						al.add(ph_no[val]);
						bl.printing(al,4,veh_type[val]);
						fl=1;
					}
					else if(outgoing.charAt(0)==time[j].charAt(0)&&outgoing.charAt(1)==time[j].charAt(1))
					{
						if(outgoing.charAt(3)==time[j].charAt(3) && outgoing.charAt(4)<time[j].charAt(4)||outgoing.charAt(3)<time[j].charAt(3))
						{
							al.add(d);
							al.add(outgoing);
							al.add(name[val]);
							al.add(ph_no[val]);
							bl.printing(al,4,veh_type[val]);
							fl=1;	
						}
						else
							fl=0;
					}
					else 
					{
						visited=1;
						fl=0;
					}
				}
					
				else 
				{
					dateextra=1;
                			fl=0;
				}	
			}
		break;
		}
	}
	if(fl==0)
	{
		System.out.print("Enter your name:  ");
        	name[i]=sc.next();
        	System.out.print("Enter your phone number:  ");
        	ph_no[i]=sc.next();
        	System.out.println("Enter the vehicle type:\n     1.Two wheeler\n     2.Car/ Three wheeled vehicle\n     3.Bus / Truck \n     4.LCV");
        	System.out.print("     5.Axle Vehicle \n     6.Commercial vehicle\n     7.Over sized vehicle\n");
        	veh_type[i]=sc.nextInt();
		if(visited==1)
		{
			str[i]=d;
			al.add(str[i]);
			time[i]=outgoing;
			al.add(time[i]);
		}
		else if(dateextra==1)
		{
			str[i]=d;
			al.add(str[i]);
			time[i]=timef.format(new Date());
			al.add(time[i]);
		}
		else
		{
			str[i] = datef.format(new Date());
			al.add(str[i]);
			time[i]=timef.format(new Date());
			al.add(time[i]);
		}
		al.add(name[i]);
		al.add(ph_no[i]);
		bl.printing(al,way,veh_type[i]);
        	obm.VEHICLE_TYPE(veh_type[i],1);
		
	}
	else
	{
		obm.VEHICLE_TYPE(veh_type[val],4);
	}
	i++;
    }
    
}



class Multitrip implements intermulti
{
    Print bl=new Print();
    Scanner sc1=new Scanner(System.in);
    String veh_no[]=new String[50];
    int veh_type[]=new int[50];
    String name[]=new String[50];
    String ph_no[]=new String[50];
    String incoming[]=new String[50];
    String outgoing;
    Date date=new Date();
    String time[]=new String[50];
    SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    String str[]=new String[50];
    SimpleDateFormat timef = new SimpleDateFormat("kk:mm");
    int in=0;
    String d;
    int n;
    int visited=0,dateextra=0;
    Main obm2=new Main();
    public int getmulti()
    {
	ArrayList<String> al=new ArrayList<String>();
        System.out.print("Enter the vehicle number: ");
        veh_no[in]=sc1.next();
        int fl=0;
	visited=0;
	dateextra=0;
        for(int i=0;i<in;i++)
        {
            if(veh_no[i].equals(veh_no[in])==true)
            {
		System.out.print("Enter date(dd/mm/yyyy): ");
		d=sc1.next();
		if(d.equals(str[i]))
		{
			n=veh_type[i];
                        al.add(veh_no[i]);
			al.add(d);
			String t=timef.format(new Date());
			al.add(t);
			al.add(name[i]);
			al.add(ph_no[i]);
			bl.printing(al,2,8);
			fl=1;
		}
		else
		{
			if((str[i].charAt(1))==d.charAt(1)-1)
			{
				System.out.print("Enter time: ");
				outgoing=sc1.next();
				if(outgoing.charAt(0)==time[i].charAt(0) && outgoing.charAt(1)<time[i].charAt(1)||outgoing.charAt(0)<time[i].charAt(0))
				{
					n=veh_type[i];
					al.add(veh_no[i]);
					al.add(d);
					al.add(outgoing);
					al.add(name[i]);
					al.add(ph_no[i]);
					bl.printing(al,2,8);
					fl=1;
				}
				else if(outgoing.charAt(0)==time[i].charAt(0)&&outgoing.charAt(1)==time[i].charAt(1))
				{
					if(outgoing.charAt(3)==time[i].charAt(3) && outgoing.charAt(4)<time[i].charAt(4)||outgoing.charAt(3)<time[i].charAt(3))
					{
						n=veh_type[i];
						al.add(veh_no[i]);
						al.add(d);
						al.add(outgoing);
						al.add(name[i]);
						al.add(ph_no[i]);
						bl.printing(al,2,8);
						fl=1;	
					}
					else
						fl=0;	
				}
				else 
				{
					visited=1;
					fl=0;
				}
			}
			else 
			{
				dateextra=1;
               			fl=0;
			}
				
                }
		break;
	    }
	    
        }
        return fl;
    }
    public void getmulti(int flag,int way)
    {
        ArrayList<String> al=new ArrayList<String>();
        if(flag==1)
        {
		String[] str={"","Two wheeler","Car","Bus","LCV","Axle Vehicle","Commercial vehicle","Over sized vehicle"};
		System.out.println(" VECHICLE TYPE : "+str[n]);
		System.out.println("\n****\n");
        }
        else
        {
	    al.add(veh_no[in]);
            System.out.print("Enter your name:  ");
            name[in]=sc1.next();
            System.out.print("Enter your phone number:  ");
            ph_no[in]=sc1.next();
            System.out.println("Enter the vehicle type:\n     1.Two wheeler\n     2.Car/ Three wheeled vehicle\n     3.Bus / Truck \n     4.LCV");
            System.out.print("     5.Axle Vehicle \n     6.Commercial vehicle\n     7.Over sized vehicle\n");
            veh_type[in]=sc1.nextInt();
	    if(visited==1)
	    {
			str[in]=d;
			al.add(str[in]);
			time[in]=outgoing;
			al.add(time[in]);
	     }
		else if(dateextra==1)
		{
			str[in]=d;
			al.add(str[in]);
			time[in]=timef.format(new Date());
			al.add(time[in]);
		}
		else
		{
			str[in] = datef.format(new Date());
			al.add(str[in]);
			time[in]=timef.format(new Date());
			al.add(time[in]);
		}
	    al.add(name[in]);
	    al.add(ph_no[in]);
	    bl.printing(al,2,veh_type[in]);
            obm2.VEHICLE_TYPE(veh_type[in],way);
            in++;
        }
        
    }
    
    
}



class Two_wheeler
{
    int amt_single=20;
    int amt_dailypass=60;
    int amt_return=10;
    Two_wheeler(int way)
    {
        if(way==1)
            System.out.println("        AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
}
class Car 
{
    int amt_single=40;
    int amt_dailypass=110;
    int amt_return=20;
    Car(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");

    }
}
class Bus
{
    int amt_single=115;
    int amt_dailypass=300;
    int amt_return=55;
    Bus(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
}
class LCV 
{
    int amt_single=65;
    int amt_dailypass=165;
    int amt_return=30;
    LCV(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
}
class Axle_vehicle
{
    int amt_single=140;
    int amt_dailypass=375;
    int amt_return=70;
    Axle_vehicle(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
    
}
class Commercial_vehicle
{
    int amt_single=170;
    int amt_dailypass=450;
    int amt_return=85;
    Commercial_vehicle(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
}
class Over_sized_Vehicle
{
    int amt_single=230;
    int amt_dailypass=600;
    int amt_return=115;
    Over_sized_Vehicle(int way)
    {
        if(way==1)
            System.out.println("	AMOUNT :  "+amt_single+"\n****\n");
        else if(way==2)
            System.out.println("	AMOUNT :  "+amt_dailypass+"\n****\n");
	else if(way==4)
	    System.out.println("	AMOUNT :  "+amt_return+"\n****\n");
    }
}
class Print
{
     	void printing(ArrayList<String> al,int way,int vehtype)
	{
		System.out.println("\n****");
		System.out.println("National Highway Authority of India\nWelcome to IVRCL -Chengapalli Tollways Ltd");
		System.out.println("           Receipt for Toll                   ");
		System.out.println("   VEHICLE NO  :  "+al.get(0));
		System.out.println("         DATE  :  "+al.get(1));
		System.out.println("         TIME  :  "+al.get(2));
		System.out.println("         NAME  :  "+al.get(3));
		System.out.println("  PHONE NUMBER :  "+al.get(4));
		
		switch(vehtype)
        	{
            	case 1:
                	System.out.println("  VEHICLE TYPE :  Two wheeler");
                	break;
            	case 2:
                	System.out.println("  VEHICLE TYPE :  Car");
                	break;
            	case 3:
                	System.out.println("  VEHICLE TYPE :  Bus");
                	break;
            	case 4:
            		System.out.println("  VEHICLE TYPE :  LCV");
                	break;
            	case 5:
                	System.out.println("  VEHICLE TYPE :  Axle_vehicle");
                	break;
            	case 6:
                	System.out.println("  VEHICLE TYPE :  Commercial_vehicle");
                	break;
            	case 7:
                	System.out.println("  VEHICLE TYPE :  Over_sized_Vehicle");
                	break;
		}
		if(way==1)
		{
			System.out.println("           WAY : SINGLE");
		}
		else if(way==2)
		{
			System.out.println("           WAY : MULTIPASS");
		}
		else if(way==4)
		{
			System.out.println("           WAY : RETURN");
		}
		else
		{
			System.out.println("           WAY : FASTAG");
		} 
		
	}
}