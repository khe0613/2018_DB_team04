package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.TheaterDAO;



// ��ȭ����������
public class Theater {
   private int branchNo;
   private int screenNum;
   private String address;
   private String tel;
   private String branchName;
   
   public Theater() {
      
   }
   
   public Theater(int branchNo, int screenNum, String address, String tel, String branchName) {
      this.branchNo = branchNo;
      this.screenNum = screenNum;
      this.address = address;
      this.tel = tel;
      this.branchName = branchName;
   }
   
   public int getBranchNo() {
      return branchNo;
   }
   
   public void setBranchNo(int branchNo) {
      this.branchNo = branchNo;
   }
   
   public int getScreenNum() {
      return screenNum;
   }
   
   public void setScreenNum(int screenNum) {
      this.screenNum = screenNum;
   }
   
   public String getAddress() {
      return address;
   }
   
   public void setAddress(String address) {
      this.address = address;
   }
   
   public String getTel() {
      return tel;
   }
   
   public void setTel(String tel) {
      this.tel = tel;
   }
   
   public String getBranchName() {
      return branchName;
   }
   
   public void setBranchName(String branchName) {
      this.branchName = branchName;
   }
   
   public void start() {
      Scanner sce = new Scanner(System.in);
      String inputMenu = "";
      Print.printMessage("------------- �� ȭ �� �� �� �� �� -------------");
      Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
      Print.printMessage("1: ��ȭ�� ���   2: ��ȭ�� ���� ����   3: ��ȭ�� ����   4. ��ȭ��������");
      inputMenu = sce.next();
      menu(inputMenu);
   }
   
   private void menu(String menu) {
      switch(menu) {
      case "1":
         Print.printMessage("-> ��ȭ�� ���");
         // ����
         if(addTheater()) {
            Print.printMessage("!! ��ȭ�� ��� ����");
         }
         else {
            Print.printMessage("!! ��ȭ�� ��� ����");
         }
         break;
      case "2":
         Print.printMessage("-> ��ȭ�� ���� ����");
         
         if(modifyTheaterInfo()) {
            Print.printMessage("!! ��ȭ�� ���� ����");
         }
         else {
            Print.printMessage("!! ��ȭ�� ���� ����");
         }
         break;
      case "3":
         Print.printMessage("-> ��ȭ�� ����");
         
         if(removeTheater()) {
            Print.printMessage("!! ��ȭ�� ���� ����");
         }
         else {
            Print.printMessage("!! ��ȭ�� ���� ����");
         }
         break;
      case "4":
         theaterList();
         break;
      default:
         break;
      }
   }
   
   // ��ȭ�� ���
   public boolean addTheater() {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("---------------- ��ȭ�� ��� ----------------");
      System.out.print("�󿵰� �� : ");   this.screenNum = sc.nextInt();
      System.out.print("�ּ� : ");      this.address = sc.next();
      System.out.print("��ȭ��ȣ : ");   this.tel = sc.next();
      System.out.print("������ : ");      this.branchName = sc.next();
      
      if((this.branchNo = new TheaterDAO().registerTheater(this)) != -1) {         // ��ȭ�� ��� ����(�����ڵ尡 ���ϵ�)
         return true;
      }else {
         return false;
      }
      

   }
   
   
   // ��ȭ�� ���� ����
   public boolean modifyTheaterInfo() {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("---------------- ��ȭ�� ���� ���� ----------------");
      System.out.print("������ ���� �ڵ� : ");
      int branchNo = sc.nextInt();
      
      System.out.print("�󿵰� �� : ");
      int new_screenNum = sc.nextInt();
      
      System.out.print("�ּ� : ");
      String new_address = sc.next();
      
      System.out.print("��ȭ��ȣ : ");
      String new_tel = sc.next();
      
      System.out.print("������ : ");   
      String new_branchName = sc.next();

      if(new TheaterDAO().modifyTheaterInfo(branchNo, new_screenNum, new_address, new_tel, new_branchName)) {
    	 this.branchNo = branchNo;
         this.screenNum = new_screenNum;
         this.address = new_address;
         this.tel = new_tel;
         this.branchName = new_branchName;
         
         return true;
      }else {
         return false;
      }
   
   }
   
   // ��ȭ�� ����
   	public boolean removeTheater() {
	      Scanner sc = new Scanner(System.in);
	      System.out.print("������ �����ڵ� : ");   
	      int remove_branchNo = sc.nextInt();
	      return new TheaterDAO().removeTheater(remove_branchNo);      // ������ ��� true, ������ ��� false�� ���ϵ�
	   }
   
   public void theaterList() {
	   TheaterDAO TheaterDAO = new TheaterDAO();
      ArrayList<Theater> result = TheaterDAO.getMovieInfoListSQL();
      
      if(result == null) {
         Print.printMessage("!! �ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
         Print.printMessage("-----------------------------------------------------");
         return;
      }
      
      Print.printMessage("�����ڵ�\t�󿵰���\t�ּ�\t\t��ȭ��ȣ\t\t������");
      int i = 0;
      while(i < result.size()) {
         Theater temp = result.get(i);
         Print.printMessage(temp.getBranchNo() + "\t" + temp.getScreenNum() + "\t" + temp.getAddress()
         + "\t" + temp.getTel() + "\t" + temp.getBranchName());
         
         i++;
      }
      Print.printMessage("�� " + i + "���� ��ȭ�� ������ ����Ǿ��ֽ��ϴ�.");
      Print.printMessage("-----------------------------------------------------");
   }
}