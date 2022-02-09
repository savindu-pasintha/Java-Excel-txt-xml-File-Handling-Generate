
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HACKER PERSON
 */
public class Sanjana_book_shop {

   public String name[];// ISBN no
   public String city[];// title
   public String price[];//price
   public String author[];//author
   public String edition[];//edition
   
   public String tempname[];
   public String tempcity[];
   public String tempprice[];
   public String tempauthor[];
   public String tempedition[];
   
   public String arr[];
   
    public void openFile(){
       try {
            File  file=new File("src/book.txt");
            file.createNewFile();
            System.out.println("File a created.");
       } catch (IOException ex) { System.out.println(ex); }
    }
    
    public int insert(String isbn, String title, String author, String edition, String price){
          int x=1;
        try{
            FileWriter fw=new FileWriter("src/book.txt", true); //file write append enable;
            //write data file inside
            arr =new String[2];
            fw.write(isbn +  "," + title +  "," + author+ "," + edition +","+ price + "," + '\n');
            fw.close();
            System.out.println("File write."); 
            
          x=1;
        }catch (IOException ex) {  System.out.println(ex); x=0;}
        
        return x;
    }
     
    public int update(String search, String newName, String newCity ,  String newauthor, String newedition, String newprice ){
            int p=1;
            int i=0; 
            //4-search the index where to update
            try{
                 //1-read data
            view(); //execute the view method
      
                // int index=0;
                i=0;
                for(i=0; i<name.length; i++){
                // System.out.println(i + "--"+ name[i]);
                 String x=name[i];
                 if(x.equals(search)){
                     name[i]=newName;
                    city[i]=newCity;
                     author[i]=newauthor;
                    edition[i]=newedition;
                     price[i]=newprice;
                   p=1;
                   break;
                 }else{
                     p=0;
                 }
                }
                    
            }catch(Exception ex){
                System.out.println(ex);
            }
              
           //6-again write data to textfile
           try{
             FileWriter nfw = new FileWriter("src/book.txt");//thin file eke data dele krl aluthin add krnw 
             for(i=0; i<name.length; i++)
             {
                 nfw.write(name[i] +  "," + city[i] +"," + author[i] +  "," + edition[i] + "," + price[i]+ "," +  '\n' );
             }
             nfw.close();
             p=1;
           }catch (IOException ex) {System.out.println(ex); p=0; }
             
        return p;
    }
     
    public int delete(String search){
    //1-read data
           view();//execute view method
              int z=1;
           int i=0;
            //4-search the index where to update
           int index=0;
          
           try{  
           
           for(i= 0; i<name.length; i++){
              
                // System.out.println(i + "--"+ name[i]);
                 String x=name[i];
                 if(x.equals(search)){
                   index=i;
                   // System.out.println(i+"-"+ name[i] + "-"+city[i]);
                   //   System.out.println("Arrays ID FOUND TO DELETE");
                   z=1;
                   break;
                 }else{
                     z=0;
                     
                 }
           }     
             
           if(z==1){
           
            //5-copy data to tempary array
            i=name.length-1;
            tempname=new String[i];
            tempcity=new String[i];
            tempauthor=new String[i];//author
            tempedition=new String[i];//edition
            tempprice=new String[i];//price
            
            for(i=0; i<index ; i++){
                    tempname[i]=name[i];
                    tempcity[i]=city[i];
                    tempauthor[i]=author[i];
                    tempprice[i]=price[i];
                    tempedition[i]=edition[i];
                   
                  // System.out.println(i+"-1-"+ tempauthor[i] + "-"+ tempcity[i]);
                }
            int end=tempname.length;
            for(i=index; i<end; i++){
                    tempname[i]=name[i+1];
                    tempcity[i]=city[i+1];
                    tempauthor[i]=author[i+1];
                    tempprice[i]=price[i+1];
                    tempedition[i]=edition[i+1];
                 //  System.out.println(i+"-1-"+ tempname[i] + "-2-"+ tempcity[i]);
                }
                 z=1;
                   
                z = rewrite();  //call in delete method rewrite temp array data in text file
              
               System.out.println(z+"-if-");  
             }else{
                z=0;
                     System.out.println(z+"-else-");
                 }  
            }
            catch(Exception ex){System.out.println(ex); z=0; }
           
          
           return z;
          
    } 
        //call in delete method rewrite temp array data in text file
    public int rewrite(){
        //6-again re-write data to textfile
        int i=0; int k=0; int index=0;
           try{
             FileWriter newfw = new FileWriter("src/book.txt"); 
             index=tempname.length;
             i=0;
             for(i=0; i<index; i++)
             {
                 newfw.write(tempname[i] + "," + tempcity[i] + "," + tempauthor[i] + "," + tempedition[i]+ "," + tempprice[i] + "," + '\n');
               //  System.out.println(i + " --- "+tempname[i] +  "," + tempcity[i]);
             }
             newfw.close();
             k=1;
           }catch (IOException ex) {System.out.println(ex); k=0; }
           
           return k;
    }
    public  DefaultTableModel  TableModel(String[] namex,String[] cityx,String[] authorx,String[] editionx,String[] pricex){
          DefaultTableModel  modelx=new DefaultTableModel();
          modelx.addColumn("ISBN");
                modelx.addColumn("TITLE");
                modelx.addColumn("AUTHOR");
                modelx.addColumn("EDITION");
                modelx.addColumn("PRICE");
                modelx.setRowCount(0);
               int c=name.length;
               int b=0;
                while(b<c){
                    Vector v=new Vector();
                    v.add(namex[b]);
                    v.add(cityx[b]);
                    v.add(authorx[b]);
                    v.add(editionx[b]);
                    v.add(pricex[b]);
                    
                    modelx.addRow(v);
                    b++;
                }
          return modelx;
    }
    public  DefaultTableModel view(){
           DefaultTableModel  model=new DefaultTableModel();
            //1-read data
            try{
            int i=0;    
            FileReader fr = new FileReader("src/book.txt");
            Scanner scanLine = new Scanner(fr);
            String textline;
            
            //2-count tle text file txt lines
            while(scanLine.hasNext()){ String x = scanLine.nextLine(); i++; }
            scanLine.close();
            fr.close();
            //System.out.println(i);
            
            //assign the arary size
            name=new String[i];
            city=new String[i];
            author=new String[i];//author
            edition=new String[i];//edition
            price=new String[i];//price
            
            //3- put txt data to pararalen arrays
            i=0;
            FileReader frnew = new FileReader("src/book.txt");
            Scanner scanLineNew = new Scanner(frnew);
            while(scanLineNew.hasNext()){
              textline=scanLineNew.nextLine();
             // System.out.println("===="+textline);
              Scanner scanWord = new Scanner(textline); //that string scan 
              scanWord.useDelimiter(","); //separate by that ","
                //'yourname','city" 
                name[i]= scanWord.next();
                city[i]=scanWord.next();
                author[i]= scanWord.next();//Author
                edition[i]=scanWord.next();//edition
                price[i]= scanWord.next();//price
                
                i++;
            }
            scanLineNew.close();
            frnew.close();
            
            //execute table model method
            model=TableModel(name,city,author,edition,price);
                      
            }catch (IOException ex) { System.out.println(ex); }
            return model; 
    }
    
     //preparing order
    public  DefaultTableModel  sortOrdre(String type){
            DefaultTableModel  model=new DefaultTableModel();
          view();// execute view method  
        //price arary string to double convert to sorting numbers
        double[] beforeSort= new double[price.length];
        int n = price.length; 
        double[] arr = new double[n];
        for(int i=0; i < n; i++){  beforeSort[i] = Double.parseDouble(price[i]); arr[i] = Double.parseDouble(price[i]);  }
     //  T[] garr;//{"1"};
      // for(int i=0; i < n; i++){  beforeSort[i] = Double.parseDouble(price[i]); arr[i] = T.parseT(price[i]);  }
         if("A".equals(type)){
            // T[]v;
           arr=bblSort(arr,n);
         }else if("D".equals(type)){
            arr=bblSort(arr,n);
            int p=0;
           double[] temparr=new double[n];
            for(int i=n-1; i>=0; i--){ temparr[p] = arr[i];   p++;}
           // System.out.println(Arrays.toString(temparr));
           for(int i=0; i<temparr.length; i++){ arr[i]=temparr[i];}
          
         }
         //double x; x=arr[i]; arr[p]=x; p++; 
       // System.out.println(Arrays.toString(beforeSort)); 
        //System.out.println(Arrays.toString(arr));
        
        int[] z =new int[n] ;// this array used to after sort array datae where the store in before arary index values
        for(int i=0; i<n; i++){for(int j=0; j<n; j++){if(arr[i] == beforeSort[j]){ z[i]=j;}}}
       // System.out.println(Arrays.toString(z));
        
        //positions chages in other array indexes
        tempname=new String[n];tempcity=new String[n];tempauthor=new String[n];tempedition=new String[n];tempprice=new String[n];//price
        
        for(int i=0; i<z.length; i++){int val=z[i]; tempname[i]=name[val];tempcity[i]=city[val];tempauthor[i]=author[val];tempedition[i]=edition[val];tempprice[i]=price[val];}
        
        //System.out.println(Arrays.toString(tempprice));
        
        model=TableModel(tempname,tempcity,tempauthor,tempedition,tempprice);
        return model;
    } 
  
    //unorderd array sort
  //  public double[] bblSort(double[] arr, int n){
     public double[] bblSort(double[] arr, int n){
        double temp = 0;
        for(int i=0; i < n; i++)
        {
            for(int j=1; j < (n-i); j++){    if(arr[j-1] > arr[j])
            {  //swap elements  
                                 temp = arr[j-1];  
                                 arr[j-1] = arr[j];  
                                 arr[j] = temp;  
                            }  
                 }  
         }
        //end bubble sort 
         
         return arr;
         
         /* public <T extends  Comparable> T[] bblSort(T[] arr, int n){
        T temp; //T[] arr=arry;
       // int j; int i=0;
      
        for(int i=0; i < n; i++)
        {  for(int j=1; j < (n-i); j++)
                 {   
                           if( arr[j-1].compareTo(arr[j]) > 0)
                            {  //swap elements  
                                 temp = arr[j-1];  
                                 arr[j-1] = arr[j];  
                                 arr[j] = temp;  
                            }  
                 }  
         }
        //end bubble sort 
         
         return  arr;
     }  */
    
     } 
    
    
    
    
   public String[] commonsearch(String searchvalue,int x){
         view();
         int n = price.length;
         String[] parts;
         String word;
        String xp;
        
         String[] Ncopyarr=new  String[n]; 
         String[] Nbeforecopyarr=new  String[n];
         
         String[] index=new String[7];
         /*default values*/
         index[0]="";index[1]="";index[2]="";index[3]="";index[4]="";index[5]="";index[6]="";
         
       switch (x) {
           
           case 1 -> {
               
                String[] isbn = searchvalue.split("-");
                searchvalue =isbn[0].concat(isbn[1]);
                for(int i=0; i < n; i++){ word=name[i]; parts=word.split("-"); word=parts[0].concat(parts[1]); Ncopyarr[i]=word; Nbeforecopyarr[i]=word;  }
                Arrays.sort(Ncopyarr);
  
                 break;
           }
           case 2 -> {
                   
               for(int i=0; i <city.length; i++){ word=city[i];   Nbeforecopyarr[i]=word; Ncopyarr[i]=word;  }
       
                 break;
           }
           case 3 -> {
               for(int i=0; i <n; i++){ word=author[i];   Nbeforecopyarr[i]=word; Ncopyarr[i]=word; }
                 break;
           }
           case 4 -> {
               for(int i=0; i <n; i++){ word=edition[i];   Nbeforecopyarr[i]=word; Ncopyarr[i]=word; }
               
                 break;
           }
             case 5 -> {
               for(int i=0; i <n; i++){ word=price[i];   Nbeforecopyarr[i]=word; Ncopyarr[i]=word; }
               
                 break;
           }
           default -> {
               
               
               break;
           }  
       }
       
       
        //binary search
       /*int i=0;
       for(i= 0; i<name.length; i++){
              
                // System.out.println(i + "--"+ name[i]);
                xp =price[i];
                 
                 if(xp.equals(searchvalue)){
                   
                   // System.out.println(i+"-"+ name[i] + "-"+city[i]);
                   //   System.out.println("Arrays ID FOUND TO DELETE");
                   index[0]=name[i];
                   index[1]=city[i];
                   index[2]=author[i];
                   index[3]=edition[i]; 
                   index[4]=price[i];
                   index[5]=String.valueOf(i);
                   index[6]=String.valueOf(i);
                   break;
                 }else{
                    
                     
                 }
           }  
       
       */
       //binary search
 
        int result = Arrays.binarySearch(Ncopyarr,searchvalue);
        if (result < 0){ System.out.println("Element is not found!");  }
        else{index[6]=String.valueOf(result);  
        System.out.println("Element is found at index: "+result);
        for(int i=0; i<n; i++){
            if( Objects.equals(Ncopyarr[result],Nbeforecopyarr[i]))
            { 
            index[0]=name[i]; index[1]=city[i];  index[2]=author[i];  index[3]=edition[i];  index[4]=price[i];  index[5]=String.valueOf(i);
        break; }}
        }

        return index;
   }
  
}




