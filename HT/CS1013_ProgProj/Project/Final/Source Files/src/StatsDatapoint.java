
public class StatsDatapoint 
{
	public static String StatsArray[][] = new String[73][6];
	public static String[] createArray()
	{
		String[] theArray = {"Bedfordshire","655000","36","1235","41","530", 
				"Berkshire","890600","24","1261","40","705",
				"Bristol","449300","43","110","47","4095",
				"Buckinghamshire","790200","30","1873","32","421",
				"Cambridgeshire","841200","27","3389","15","248",
				"Cheshire","1043500","19","2342","25","445",
				"City of London","8800","48","2.9","48","3020",
				"Cornwall","551700","40","3562","12","154",
				"Cumbria","498000","41","6766","3","73",
				"Derbyshire","1036600","20","2624","21","394",
				"Devon","1169200","11","6706","4","174",
				"Dorset","765700","31","2652","20","288",
				"Durham","855900","26","2675","19","319",
				"East Yorkshire","595700","37","2476","23","240",
				"East Sussex","829300","29","1791","33","462",
				"Essex","1787000","7","3669","11","486",
				"Gloucestershire","891800","23","3149","16","283",
				"Greater London","8665000","1","1569","37","5521",
				"Greater Manchester","2756200","3","1275","39","2160",
				"Hampshire","1814300","5","3769","9","481",
				"Herefordshire","188100","45","2179","26","86",
				"Hertfordshire","1166300","13","1643","36","709",
				"Isle of Wight","139400","46","380","46","366",
				"Kent","1801200","6","3738","10","481",
				"Lancashire","1478100","8","3075","17","480",
				"Leicestershire","1017900","21","2156","28","472",
				"Lincolnshire","1066100","18","6975","2","152",
				"Merseyside","1398000","9","647","43","2161",
				"Norfolk","885000","25","5380","5","164",
				"North Yorkshire","1140400","14","8654","1","131",
				"Northamptonshire","723000","33","2364","24","305",
				"Northumberland","315300","44","5013","6","62",
				"Nottinghamshire","1124700","15","2159","27","520",
				"Oxfordshire","677800","35","2604","22","260",
				"Rutland","38000","47","382","45","99",
				"Shropshire","482500","42","3487","13","138",
				"Somerset","940200","22","4170","7","225",
				"South Yorkshire","1374700","10","1551","38","885",
				"Staffordshire","1114200","17","2713","18","410",
				"Suffolk","741900","32","3800","8","195",
				"Surrey","1168800","12","1662","35","703",
				"Tyne and Wear","1122200","16","540","44","2078",
				"Warwickshire","554000","39","1975","31","280",
				"West Midlands","2833600","2","902","42","3142",
				"West Sussex","836300","28","1990","30","420",
				"West Yorkshire","2281700","4","2029","29","1124",
				"Wiltshire","703300","34","3485","14","201",
				"Worcestershire","578600","38","1740","34","332",
				"Conwy","116200","n/a","1130","n/a","103",
				"Wrexham","136607","n/a","499","n/a","271",
				"Powys","132602","n/a","5179","n/a","25",
				"Flintshire","154074","n/a","438","n/a","351",
				"Merionethshire","n/a","","","n/a","",
				"MONMOUTHSHIRE","92476","n/a","850","n/a","108",
				"Montgomeryshire","","","","n/a","",
				"PEMBROKESHIRE","123464","n/a","1590","n/a","76",
				"Radnorshire","","n/a","","n/a","",
				"Isle of Anglesey","69979","n/a","714","n/a","98",
				"Denbighshire","94691","n/a","844","n/a","113",
				"Gwynedd","122864","n/a","2548","n/a","48",
				"Cardiganshire","74642","n/a","1783","n/a","41",
				"Carmarthenshire","185123","n/a","2395","n/a","78",
				"Swansea","242382","n/a","380","n/a","601",
				"Neath Port Talbot","140992","n/a","442","n/a","319",
				"Rhondda Cynon Taff","237411","n/a","424","n/a","559",
				"Merthyr Tydfil","59324","n/a","-","n/a","-",
				"Blaenau Gwent","69544","n/a","109","n/a","639",
				"Caerphilly","180164","n/a","278","n/a","649",
				"Torfaen","91836","n/a","126","n/a","730",
				"Bridgend","142092","n/a","-","n/a","-",
				"Newport","147769","n/a","190","n/a","778",
				"Cardiff","357160","n/a","140","n/a","2551",
				"Vale of GLAMORGAN","127592","n/a","335","n/a","385"};
		
		return theArray;
	}
	public static void create2DArray()
	{
		int j=0;
		String[]pointInfo = createArray();
		for(int i=0;i<73;i++)
		{
			StatsArray[i][0] = pointInfo[j];
			j++;
			StatsArray[i][1] = pointInfo[j];
			j++;
			StatsArray[i][2] = pointInfo[j] ;
			j++;
			StatsArray[i][3] = pointInfo[j] ;
			j++;
			StatsArray[i][4] = pointInfo[j] ;
			j++;
			StatsArray[i][5] = pointInfo[j] ;
			j++;
		}
	}
}
