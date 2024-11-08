/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aos6;
import java.util.*;
public class Aos6
{
double avgst,totst;
void fcfs(int a[],int p)
{
totst=Math.abs(p-a[0]);
for(int i=0;i<a.length-1;i++)
{
totst=Math.abs(a[i]-a[i+1]);
}
avgst=totst/a.length;
System.out.println("Total seek time:"+totst);
System.out.println("Average seek time:"+avgst);
}
void sstf(int a[],int p)
{
boolean done[]=new boolean[a.length];
int strtpt=p;
while(!allSelected(done))
{
int m=0;
int mindiff=1000;
for(int i=0;i<a.length;i++)
{
if(!done[i])
{
int diff=Math.abs(strtpt-a[i]); if(diff<mindiff)
{
m=i; mindiff=diff;
}
}
}
totst+=mindiff; strtpt=a[m];
done[m]=true;
}
avgst=totst/a.length; System.out.println("Total seek time:"+totst);
System.out.println("Average seek time:"+avgst);
}
boolean allSelected(boolean[]a)
{
for(int i=0;i<a.length;i++) if(!a[i])
return false; return true;
}
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
int c;
do
{
System.out.println("Enter no. of elements:"); int n=sc.nextInt();
System.out.println("Enter value of track nos. in the array"); int
a[]=new int[n];
for(int i=0;i<n;i++) a[i]=sc.nextInt();
System.out.println("Enter startingt point");
int p=sc.nextInt();
System.out.println("\n Enter your choice:\n 1:FCFS\n 2:SSTF");
int ch=sc.nextInt();

Aos6 ds=new Aos6(); if(ch==1)
ds.fcfs(a,p);
else ds.sstf(a,p);
System.out.println("\n Do u wanna continue.(1/0)");
c=sc.nextInt();
}
while(c==1);
}
}
