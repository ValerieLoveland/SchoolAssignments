//Valerie Loveland Project 1 CS472

import java.io.*;


public class Disassembler {

	public static void main(String[] args) throws FileNotFoundException {
		
		PrintStream printOut = new PrintStream("lovelandProject1.txt");
		System.setOut(printOut);
		int hexnum;
		String sign=null;
		int isrc1, isrc2, ifunct, ioffset;
		int rsrc1, rsrc2, rfunct, rdes;
		int address= 0x9A040;
		
		int arr[];
		arr = new int[11];
		arr[0]=0x032BA020;
		arr[1]=0x8CE90014;
		arr[2]=0x12A90003;
		arr[3]=0x022DA822;
		arr[4]=0xADB30020;
		arr[5]=0x02697824;
		arr[6]=0xAE8FFFF4;
		arr[7]=0x018C6020;
		arr[8]=0x02A4A825;
		arr[9]=0x158FFFF7;
		arr[10]=0x8ECDFFF0;
		
		
		for (int i=0; i<arr.length; i++) {
		hexnum= arr[i];	
		

		
		int opcode =  (hexnum >>>26);
		if (opcode == 0) {
			rsrc1 = (hexnum & 0x03e00000) >> 21;
			rsrc2 =  (hexnum & 0x1f0000)>>16;
			rdes =  (hexnum & 0xf800) >>> 11;
			rfunct = (hexnum & 0x3f);
			
			if (rfunct ==0x20) {
				sign ="ADD";}
			
			if (rfunct ==0x22) {
				sign ="SUB";}
			
			if (rfunct ==0x24) {
				sign ="AND";}
			
			if (rfunct ==0x25) {
				sign ="OR";}
			System.out.printf("0x%02X", address);
			System.out.println(": "+sign+ " $"+ rdes +", " + "$"+ rsrc1 +", "+"$"+ rsrc2);
			address= address+0x04;}		 
			
		else {
			isrc1 =  (hexnum & 0x3e00000) >>> 21;
			isrc2 =  (hexnum & 0x1f0000)>>>16;
			ioffset = (byte) (hexnum & 0xffff);
			int branch =((ioffset<<2)+address+0x04);
			
			if (opcode == 0x23) {
				sign ="LW";}
			
			if (opcode == 0x2b) {
				sign ="SW";}
			
			if (opcode == 0x5) {
				sign ="BNE";}
			
			if (opcode == 0x4) {
				sign ="BEQ";}
			
			
			switch(sign) {
			
			
			case "LW":
			System.out.printf("0x%02X", address);
			System.out.println(": "+sign+ " $"+ isrc2  +", " + ioffset + "(" +"$"+ isrc1 +")");
			address=address+0x04;
			break;
			
			case "SW":
			System.out.printf("0x%02X", address);
			System.out.println(": "+sign+ " $"+ isrc2  +", " + ioffset + "(" +"$"+ isrc1 +")");
			address=address+0x04;
			break;
			
			case "BNE":
			System.out.printf("0x%02X", address);
			System.out.print(": "+sign+ " $"+ isrc1 +", " + "$"+ isrc2 +", "+ "address: ");
			System.out.printf("0x%02X", branch);
			System.out.println();
			address=address+0x04;
			break;
			
			case "BEQ":
			System.out.printf("0x%02X", address);
			System.out.print(": "+sign+ " $"+ isrc1 +", " + "$"+ isrc2 +", "+ "address: ");
			System.out.printf("0x%02X", branch);
			System.out.println();
			address=address+0x04;
			break;
			}
			
		}

	}

}}


