package com.AHairTeeter.Tool.Crawler.TestFilter;

import java.util.Scanner;

import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class TestFilterMain {
	/**
	 * ����ɸѡ��� �ǽ���ȡ����ҳ��Դ���뱣�浽���غ�����������ݻ�ȡ����ʹ��
	 * 
	 * @param text
	 */
	public void Subscreen() {
		Route rou = new Route();
		Scanner input = new Scanner(System.in);
		IOLocalFile ioLF = new IOLocalFile();
		System.out.println("�ļ���Ϊ:");
		String url = input.next();
		url = ioLF.output(rou.���ش洢��ַ + url + ".txt");
		System.out.println("�Ƿ�ʼɸѡֵ(Y/N)");
		String yn = input.next();
		if (yn.equals("Y") || yn.equals("y")) {
			System.out.println("����ɸѡ����!!");
			Filtrate(url);
		}
	}

	public void Filtrate(String test) {
		Scanner input = new Scanner(System.in);
		System.out.println("ɸѡ��������:");
		System.out.println("1.");
		System.out.println("3.");
		System.out.println("4.");
		String nom = input.next();
		switch (nom) {
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			break;
		case "6":
			break;
		case "8":
			break;
		case "9":
			break;
		default:
			break;
		}
	}
}
