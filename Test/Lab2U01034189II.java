/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package lab2u01034189i;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Lab2U01034189II {
    	private static int largestROI(int n, int status[][], int row1, int col1)
    { 		
                
		int trackRow[] = new int[n*n];
		int trackColumn[] = new int[n*n];
		boolean visitedColorArr[][] = new boolean[n][n];
		int rowVisitedCheck[] = new int[n*n];
		int columnVisitedCheck[] = new int[n*n];
		int sameStatusRegion = 0;
		int totalSize = 0;
                int row = 0;
                int col = 0;
		int horizontalDirection[]= {0,1,0,-1};        
		int verticalDirection[]= {1,0,-1,0};        
		
		for(int i=row;i<=row1;i++){
			for(int j=col;j<=col1;j++){
				if(visitedColorArr[i][j]==true) 
                                    continue;				
				int checker = 1;
				trackRow[checker]=i;
				trackColumn[checker]=j;
				totalSize=1;
				visitedColorArr[i][j]=true; 
				while(checker>0){
					int contiguous=0;
					for(int a=1;a<=checker;a++){
						for(int x=0;x<horizontalDirection.length;x++) {
							int i1=trackRow[a] + verticalDirection[x];
							int j1=trackColumn[a] + horizontalDirection[x];
							if(i1<row || i1>row1 || j1<col || j1>col1) 
                                                            continue;
							if(status[i1][j1]!=status[i][j]) 
                                                            continue;
							if(visitedColorArr[i1][j1]) 
                                                            continue;
							visitedColorArr[i1][j1] = true;
							contiguous++;
							rowVisitedCheck[contiguous]=i1;
							columnVisitedCheck[contiguous]=j1;								
						}
					}
					if(contiguous==0) break;
					checker = contiguous;
					totalSize+=checker;
					if(totalSize>sameStatusRegion){
						sameStatusRegion=totalSize;
					} 
					for(int a=1;a<=checker;a++) {
						trackRow[a] = rowVisitedCheck[a];
						trackColumn[a] = columnVisitedCheck[a];
					}
				}
			}
		}
		return sameStatusRegion;
    }
	public static void main(String[] args) throws Exception{
                FileReader fileReader=new FileReader("sensor2.txt"); 
		BufferedReader bufferedReader = new BufferedReader(fileReader); 
                Scanner scanFile = new Scanner(fileReader);
                int n = scanFile.nextInt();
		boolean checkNumbers[] = new boolean[Integer.MAX_VALUE/2145];
		int storeNums[] = new int[n*n];
		
		int [][] colors_arr=new int[n][n];
		int[][] colors_arr_status=new int[n][n];
		int numberofElements = 0;
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                                colors_arr[i][j] = scanFile.nextInt();
				colors_arr_status[i][j]=colors_arr[i][j];
                
				if(checkNumbers[colors_arr[i][j]] == false){
	    		checkNumbers[colors_arr[i][j]]=true;
	    		storeNums[numberofElements++]=colors_arr[i][j];
	    		}
            }
        }
		
		fileReader.close();
		scanFile.close();
		
		
		int sameStatusRegion = largestROI(n, colors_arr_status, n-1, n-1);		
		
		int differentStatusRegion = sameStatusRegion;
		for(int i=0;i<numberofElements-1;i++){
			for(int j=i+1;j<numberofElements;j++){
				for(int x=0;x<n;x++){
					for(int y=0;y<n;y++){
						if(colors_arr[x][y]==storeNums[i]){
							colors_arr_status[x][y] = storeNums[j];
						}
						else{
							colors_arr_status[x][y] = colors_arr[x][y];
						}
					}
				}
				int size = largestROI( n, colors_arr_status, n-1, n-1);
				if(size > differentStatusRegion) {
					differentStatusRegion = size;
				}
			}
		}
                
               

		System.out.println(sameStatusRegion);
		System.out.println(differentStatusRegion);
		
		File outfile = new File("sensor2out.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outfile));
		bufferedWriter.write(Integer.toString(sameStatusRegion)+"\n"+ Integer.toString(differentStatusRegion)+"\n");
		bufferedWriter.close();	
	}
}
