package com.focus.supporters;



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.focus.base.BaseEngine;

public class NotePadReader extends BaseEngine {

	

		public static String getNotePadTxt()
		{
			String data = "";
			try {

				String name = "";
				FileInputStream fstream = new FileInputStream(".\\rma.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				while ((name = br.readLine()) != null) {
					data += name + "\n";

				}

				in.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
			return data;
		}
		
		
		public static String getNotePadTxt2()
		{
			String data = "";
			try {

				String name = "";
				FileInputStream fstream = new FileInputStream(".\\rma2.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				while ((name = br.readLine()) != null) {
					data += name + "\n";

				}

				in.close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
			return data;
		}
		public static void main(String[] args) {
			
			String str=getNotePadTxt();
			System.err.println(str);
			
		}

		}
