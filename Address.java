/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Aos5
{
public static final int ADDRESS_SIZE=32;
public static void main(String[]args)
{
try
{
if(args.length!=2)
{
System.out.println("Usage:java Address<page size><address>");
}
System.out.println("Please enter the parameters <pagesize><address>");
BufferedReader bt=new BufferedReader(new
InputStreamReader(System.in)); int
pageSize=Integer.parseInt(bt.readLine().trim());
int
address=Integer.parseInt(bt.readLine().trim());
int pageBits=0;
int pageMask=0;
int offsetMask=0;
switch(pageSize)
{
case 1024:
pageBits=10;
offsetMask=0x000003ff;
pageMask=0xfffffc00; break;
case 2048:
pageBits=11;
offsetMask=0x000007ff;
pageMask=0xfffff800; break;
case 4096:
pageBits=12;
offsetMask=0x00000fff;
pageMask=0xfffff000;
break;
case 8192:
pageBits=13;
offsetMask=0x00001fff;
pageMask=0xffffe000;
break;
case 16384: pageBits=14;
offsetMask=0x000003ff;
pageMask=0xfffffc00; break;
}
int pageNumber=(address &
pageMask)>>pageBits; int offset=(address &
offsetMask);
System.out.println("For address"+address+":pageNumber="+pageNumber+"offset="+offset);
}
catch(Exception e){}}}
