package theater;



import java.util.Scanner;

import dao.MemberDAO;
import dao.MemberDAO.loginResult;

public class Member {
   private String id;
   private String pw;
   private String name;
   private String birth;
   private String address;
   private String phoneNum;
   private int point;
   
   public Member() {
      
   }
   
   public Member(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
      this.id = id;
      this.pw = pw;
      this.name = name;
      this.birth = birth;
      this.address = address;
      this.phoneNum = phoneNum;
      this.point = point;
   }
   
   public String getId() {
      return id;
   }
   
   public void setId(String id) {
      this.id = id;
   }
   
   public String getPw() {
      return pw;
   }
   
   public void setPw(String pw) {
      this.pw = pw;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getBirth() {
      return birth;
   }
   
   public void setBirth(String birth) {
      this.birth = birth;
   }
   
   public String getAddress() {
      return address;
   }
   
   public void setAddress(String address) {
      this.address = address;
   }
   public String getPhoneNum() {
      return phoneNum;
   }
   
   public void setPhoneNum(String phoneNum) {
      this.phoneNum = phoneNum;
   }
   
   public int getPoint() {
      return point;
   }
   
   public void setPoint(int point) {
      this.point = point;
   }
   
   public boolean AdminCheck(String id) {
      if(id.equals("root")) 
         return true;
      else
         return false;
   }
   
   // �α���
   public Member login() {
      Member member = new Member();
      Scanner sc = new Scanner(System.in);
      
      System.out.println("---------------- �� �� �� ----------------");
      System.out.print("���̵� : ");      this.id = sc.next();
      System.out.print("��й�ȣ : ");   this.pw = sc.next();
      
      loginResult result = new MemberDAO().loginMember(this);
      switch(result) {
      case SUCCESS:
         Print.printMessage("�α��ο� �����Ͽ����ϴ�.");
         member.setId(this.id);
         member.setPw(this.pw);
         return member;
      case DB_FAILED:
         Print.printMessage("�α��ο� �����Ͽ����ϴ�.");
         Print.printMessage("���л��� : DB ó�� ���� �� ���� �߻�");
         return null;
      case INVALID_PW:
         Print.printMessage("�α��ο� �����Ͽ����ϴ�.");
         Print.printMessage("���л��� : ���� �ʴ� ��й�ȣ �Է�");
         return null;
      case NOT_ID_IN_DB:
         Print.printMessage("�α��ο� �����Ͽ����ϴ�.");
         Print.printMessage("���л��� : �Է��Ͻ� ���̵� �������� ����");
         return null;
      }
      return null;
      
   }
   // ȸ�� ����
   public Member register() {
      Scanner sc = new Scanner(System.in);
      
      System.out.println("---------------- ȸ�� ���� ----------------");
      System.out.print("���̵� : ");      this.id = sc.next();
      System.out.print("��й�ȣ : ");   this.pw = sc.next();
      System.out.print("���� : ");      this.name = sc.next();
      System.out.print("������� : ");   this.birth = sc.next();
      System.out.print("�ּ� : ");      this.address = sc.next();
      System.out.print("��ȭ��ȣ : ");   this.phoneNum = sc.next();
      this.point = 0;
      
      Member member = new Member();
      if(new MemberDAO().registerMember(this)) {
         member.setId(this.id);
         member.setPw(this.pw);
         return member;                           // ����
      }else {
         return null;                           // ����
      }
      
   }
   
   public void MemberModifyStart() {
      Print.printMessage("------------------ ȸ �� �� �� �� �� ------------------");
      Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
      Print.printMessage("1: ȸ�� ���� ����   2: ȸ�� Ż��");
      
      Scanner sc = new Scanner(System.in);
      String menu = sc.next();
      
      // ȸ�� ���� ����
      if(menu.equals("1")) {
         if(modify()) {
            Print.printMessage("!! ȸ�� ���� ���� ����");
         }
         else
            Print.printMessage("!! ȸ�� ���� ���� ����");
      }
      // ȸ�� Ż��
      if(menu.equals("2")) {
         if(delete()) {
            Print.printMessage("!! ȸ�� Ż�� ����");
            Movie.setLogin(false);
         }
         else {
            Print.printMessage("!! ȸ�� Ż�� ����");
         }
      }
   }
   
   // ȸ�� Ż��
   public boolean delete() {
      return new MemberDAO().deleteMember(this.id);   // ������ ��� true, ������ ��� false�� ���ϵ�
   }
   
   // ȸ�� ���� ����
   public boolean modify() {
      Scanner sc = new Scanner(System.in);
      
      
      System.out.println("---------------- ȸ�� ���� ���� ----------------");
     
      
      System.out.print("��й�ȣ : ");
      String new_pw = sc.next();
      
      System.out.print("���� : ");
      String new_name = sc.next();
      
      System.out.print("������� : ");
      String new_birth = sc.next();
      
      System.out.print("�ּ� : ");
      String  new_address = sc.next();
      
      System.out.print("��ȭ��ȣ : ");
      String new_phoneNum = sc.next();

      if(new MemberDAO().modifyMember(this.id, new_pw, new_name, new_birth, new_address, new_phoneNum)) {   // ����
         this.pw = new_pw;
         this.name = new_name;
         this.birth = new_birth;
         this.address = new_address;
         this.phoneNum = new_phoneNum;
   
         return true;
      }else {
         return false;
      }
   }

   
}