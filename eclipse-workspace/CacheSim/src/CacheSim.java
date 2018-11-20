import java.util.*;
public class CacheSim {

	public static void main(String[] args) {
		//Setting up everything***********************************************************
		int[] readArray = {0x005,0x006,0x007,0x14c,0x14d,0x14e,0x14f,0x150,0x151,0x3a6,0x4c3,0x582,0x348,0x3f,0x14b,0x14c,0x63f,0x83};
		int[] writeArray = {0x14c, 0x99, 0x63B, 0x7};
		char[] directionsArray = {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'D', 'W', 'W', 'R', 'D', 'R', 'R', 'D', 'R', 'R', 'R', 'R', 'D'};
		
		int cache[][];
		cache = new int[18][22];
		int MM[]= new int[2048];
		String cacheHead[];
		cacheHead = new String[8];
		int row =0;
		int column =0;
		int tagBit = 0;
		int readInput=0;
		int writeInput=0;
		int writeAddress =0;
		String hitOrMiss="miss";
		int inputData=0;
		int dataInside=0;
		//int arrayPieceBegin=0;
		//int arrayPieceEnd=0;
		int cacheSlot =0;
		int wholeBlockStarts=0;
		int wholeBlockEnds=0;
		int d=0;
		int w=0;
		int r=0;
		char choice='R';
		
		Scanner reader = new Scanner(System.in); 
		
		//This part fills in 0-ff 7 times in MM*************************************
			for (int tag=0;tag<=7; tag++) {
			for (int i=0;i<=0xff; i++) {//if I make this 0x7ff it will show the address of the whole thing in hex******
				MM[i]=i;
				
			//I don't need this to print so I am taking this out for now. this is to debug********************************
//				System.out.printf("%02x",MM[i]);//This is to just print the value in each element in MM inhex*********************
		//		System.out.println(); 
			}
		
		}	/*for(int i = 0; i<=0xF; i++){

			    for(int j = 0; j<26; j++){
			       // System.out.printf("%2x\t",cache[i][j]);//this is too big of tabs************************
			       //cache[i][4] /t;
			  this doesn't work trying to put a tab in one spot*****************************************\    
			    }*/
		
			
			
			//Here is the part for asking questions*****************************************
			for(d=0; d<30;d++) {
			directionsArray[d]=choice;
			System.out.println("(R)ead, (W)rite, or (D)isplay Cache?");
			
		if (choice=='D') {
		//Here is the heading for the cache****************************************
		for (int i=0;i<8; i++) {
			cacheHead[i]=" ";
		}
		for (int i=0;i<=0xF;i++)
		cache[i][0]=(i);
		
		cacheHead[0]= "slot";
		cacheHead[2]= "dirty";
		cacheHead[4]= "valid";
		cacheHead[6]= "tag";
		cacheHead[7]= "data";
		for (String cacheHeads : cacheHead) {
			System.out.print(cacheHeads);}
		System.out.println(); //make a space between heading********************************
		
		
		
		//System.out.print(cache[0][0]);
		for(int i = 0x0; i<=0xff; i++){

		    for(int j = 0x0; j<0x14; j++){
		       // System.out.printf("%2x\t",cache[i][j]+"     ");//this is too big of tabs************************
		        System.out.printf("%2x",cache[i][j]);
		        //System.out.print("     ");
		      
		    }
		    System.out.println();
		    d++;
		    //break;
		    }
		
		}
		
				
		
		//make READ here*******************************************
		
				 if (choice=='R') {
				
		System.out.println("What do you want to read?");
		System.out.println("R");
		int addyInput = readArray[r];
		
		System.out.printf("0x%03X", addyInput);
		int offset=(addyInput & 0x00f);
		
		int tag=(addyInput & 0xf00)>>>8;
		int slots = (addyInput & 0x0f0)>>>4;
			int tagAndSlot=(addyInput & 0xff0);
			int slotAndOffset=(addyInput & 0x0ff);
		int wholeblockstarts = tagAndSlot;	
		
		int validBit =cache[slots][2];
		
		if ((cache[slots][2] == 0) || (tag != cache[slots][3])) {
		hitOrMiss= "Miss";
		
		
		for (int i=4;i<20; i++){
//****************************************************************************************************************************************************		
//*****************************************************************************************************************************************************
//*****************************************************************************************************************************************************
		cache[slots][i]= MM[wholeBlockStarts];
	wholeBlockStarts=wholeBlockStarts +1;
	//tagAndSlot=tagAndSlot+1;
		//cache[slots][i]= MM[slots];
		//slots=slots +1;
				}
		System.out.println("At that byte there is the value "+ cache[slots][offset] + " (Cache " + hitOrMiss + ")");
		//System.out.println(wholeBlockStarts);
		
		cache[slots][2]=1;//valid bit
				validBit=1;
				cache[slots][3]=(addyInput & 0xf00)>>>8;
				
				
				
				//break;
			}
	else {
		hitOrMiss= "Hit";
		System.out.println();
		System.out.println("The data in that bit is: " + cache[slots][offset] +"(Cache " + hitOrMiss + ")");
		
		cache[slots][2]=1;
		r++;
		//break;
		}

		//d++;
		
		
			}	
			

	//make write here***************************************************************************************************************************
 //*********************************************************************************************************************************************
//***********************************************************************************************************************************************
 if (choice=='W'){
	 int addyInput = writeArray[w];
	 System.out.println("What address would you want to write to?");		
	 writeAddress=writeArray[w];	
	 System.out.printf("0x%03X", writeAddress);
	 System.out.println();
	 System.out.println("What data would you want to write at that address?");
	 int writeData=writeArray[w+1];
	 System.out.printf("0x%03X", writeData);
	 System.out.println();
 
int offset=(writeAddress & 0x00f);
int tag=(writeAddress & 0xf00)>>>8;
int slots = (writeAddress & 0x0f0)>>>4;
int tagAndSlot=(writeAddress & 0xff0);
int slotAndOffset=(writeAddress & 0x0ff);
int wholeblockstarts = tagAndSlot;	

int validBit =cache[slots][2];

if ((cache[slots][2] == 0) || (tag != cache[slots][3])) {
	for (int i=4;i<20; i++){
hitOrMiss= "Miss";
cache[slots][i]= MM[wholeBlockStarts];
wholeBlockStarts=wholeBlockStarts +1;
	}
System.out.println("Value "+ inputData + "has been written to address"+ "(cache " + hitOrMiss + ")");
//MM[x5A1234]=6. 
cache[slots][1]= 1; //dirty bit
cache[slots][2]= 1; //valid bit
cache[slots][3]=tag;
//break;
}
else {
hitOrMiss="Hit";
cache[row][column]=inputData;
System.out.println("Value "+ inputData + "has been written to address"+ "(cache " + hitOrMiss + ")");
//MM[addyInput]=writeArray[w]; 
cache[slots][1]= 1; //dirty bit
cache[slots][2]= 1; //valid bit
cache[slots][3]=tag;}
//d++;
w++;
//break;
}break;}}}

/*this is to writeback the cache into the MM (what is i and j here?)
for (int i = 0; i < 16; i++) 
for (int j=0; j<16; j++)
{
MM[i] = cache[slot][j];
d++;
w++; 
}}



*/
