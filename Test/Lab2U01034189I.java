/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package lab2u01034189i;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Asus
 */
public class Lab2U01034189I {

      private static boolean getObj(int n, char colorArr[][], int row1, int col1, int row2, int col2)
    { 
		
		int trackRow[] = new int[n*n];
		int trackColumn[] = new int[n*n];
                int rowVisitedCheck[] = new int[n*n];
		int columnVisitedCheck[] = new int[n*n];
		
                boolean visitedColorArr[][] = new boolean[n][n];
		
		int horizontalDirection[] = {0,1,0,-1};         
		int verticalDirection[] = {1,0,-1,0};         
		int count = 0;
		
		char checkColor[] = new char[3];
		for(int i=row1;i<=row2;i++){
			for(int j=col1;j<=col2;j++){
				if(visitedColorArr[i][j] == true) 
                                    continue;
				if(count >= 3) 
                                    return false;
				checkColor[count++] = colorArr[i][j];
				int checker = 1;
				trackRow[checker] = i;
				trackColumn[checker] = j;
				visitedColorArr[i][j] = true; 
				while(checker>0){
					int contiguous=0;
					for(int a=1;a<=checker;a++){
						for(int b=0;b<horizontalDirection.length;b++) {
							int i1 = trackRow[a] + verticalDirection[b];
							int j1 = trackColumn[a] + horizontalDirection[b];
							if(i1<row1 || i1>row2)
                                                            continue;
                                                        if(j1<col1 || j1>col2)
                                                            continue;
							if(colorArr[i1][j1]!=colorArr[i][j]) 
                                                            continue;
							if(visitedColorArr[i1][j1]) 
                                                            continue;
							visitedColorArr[i1][j1] = true;
							contiguous+=1;
							rowVisitedCheck[contiguous] = i1;
							columnVisitedCheck[contiguous] = j1;								
						}
					}
					if(contiguous==0) 
                                            break;
					checker = contiguous;
					for(int a=1;a<=checker;a++) {
						trackRow[a] = rowVisitedCheck[a];
						trackColumn[a] = columnVisitedCheck[a];
					}
				}
			}
		}		
		
		if(count < 3) 
                    return false;
		if(checkColor[0]==checkColor[1] || checkColor[0]==checkColor[2])
			return true;
                if(checkColor[1]==checkColor[2])
                        return true;
		else
			return false;
    }
   
	public static void main(String[] args) throws Exception {
                FileReader fileReader=new FileReader("sensor1.txt"); 
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		int total_objects = 0;
                int n = Integer.parseInt(bufferedReader.readLine());
		
                char colors_arr[][] = new char[n][n];
		
		int row1[] = new int[n*n];
		int row2[] = new int[n*n];

		int col1[] = new int[n*n];
		int col2[] = new int[n*n];
                
                for(int i=0;i<n;i++) {
			line = bufferedReader.readLine();
			for(int j=0;j<n;j++) {
				colors_arr[i][j] = line.charAt(j);
			}	
                        
	    }
		bufferedReader.close();
		
		
		for(int x = n; x>0; x--){// 0 theke check
			for(int y = x; y>0; y--){
				for(int i=0;i<=n-x;i++){
					for(int j=0;j<=n-y;j++){
						int k=0;
						for(k=0;k<total_objects;k++){ 
							if(i>=row1[k] && i+x-1<=row2[k] && j>=col1[k] && j+y-1<=col2[k]) 
                                                            break;
						}
						if(getObj( n, colors_arr,i, j, i + x - 1, j + y - 1) && k>=total_objects){
							row1[total_objects] = i; 
							row2[total_objects] = i+x-1; 
							col1[total_objects] = j;
							col2[total_objects] = j+y-1;
							total_objects+= 1;
						}
					}
				}
				
				if(x!=y) {
					for(int i=0;i<=n-y;i++){
						for(int j=0;j<=n-x;j++){
							int k=0;
							for(k=0;k<total_objects;k++) {// Grid theke ber hoise kina
								if(i>=row1[k] && i+y-1<=row2[k] && j>=col1[k] && j+x-1<=col2[k]) 
                                                                    break;
							}
							if(getObj(n, colors_arr,i, j, i + y - 1, j + x - 1) && k>=total_objects){
								row1[total_objects] = i; 
								row2[total_objects] = i+y-1; 
								col1[total_objects] = j;
								col2[total_objects] = j+x-1;
								total_objects+=1;
							}
						}
					}
				}				
			}
		}
		
                System.out.println(total_objects);
                BufferedWriter writer = new BufferedWriter(new FileWriter("sensor1out.txt"));
                writer.write(Integer.toString(total_objects));
		writer.close();		
	}
}
