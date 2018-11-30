package phonebook


import java.util.List

import groovy.transform.Field


@Field def nameData = ["Park SH", "Kang JH",
	"Kim KS", "Lee YH", "Kang SH","Bae JM",
	"Lee DI", "Lee BH", "Jang WH", "Chun WY"];
@Field def numberData = [5023, 5002, 5008,
	5067, 5038, 5381, 5125, 5165, 5684, 5752];

@Field def List<Entry> listPhoneBook = []


//�̸��� ��� �ִ� �� ã��
int findLoc(String name){
	for (int i=0; i<listPhoneBook.size(); ++i){
		if (listPhoneBook[i].phoneName == name)
			return i;
	}
	return -1; // not found
}

//��ȣ�� ��� ��ġ�ϴ� �� Ȯ��
int findLoc2(int number){
	for (int i=0; i<listPhoneBook.size(); ++i){
		if (listPhoneBook[i].phoneNumber.equals(number))
			return i;
	}
	return -1; // not found
}

//��ȣ�κ��� �̸��� �˾Ƴ�
String findPhoneName(int number){
	int loc = findLoc2(number);
	if (loc == -1)
		return null; // not found
	return listPhoneBook[loc].phoneName;
}

//�̸����κ��� ��ȣ�� �˾Ƴ�
int findPhoneNumber(String name){
	int loc = findLoc(name);
	if (loc == -1) {
		println "***Error -- Not found";
		return -1; // not found
	}
	return listPhoneBook[loc].phoneNumber;
}

//�ּҷϿ� ���� �̸��� ���� �� �߰��Ѵ�.
void insert(String name, int number){
	int loc = findLoc(name);
	if (loc == -1){ // insert
		listPhoneBook.add(new Entry(name,number))	
	}
	else {
		println "***Error -- Duplicated Name";
	}
}

//�ּҷϿ� �̸��� ���� ��� �����Ѵ�.
void remove(String name){
	int loc = findLoc(name);
	if (loc != -1){ // remove
		listPhoneBook.remove(loc);
	}
	else {
		println "***Error -- Name not found";
	}
}

//�ּҷϿ� �̸��� ���� ��� ��ȣ�� �ٲ۴�.
void update(String name, int number){
	int loc = findLoc(name);
	if(loc != -1) {
		listPhoneBook[loc].phoneNumber = number;
	} else {
		println "***Error -- Name not found";
	}
}

//�ּҷ��� ��� ����� ��ȣ�� ���
void listAll(){
	println "Name\tNumber";
	for (int i=0; i<listPhoneBook.size(); ++i){
		println "${listPhoneBook[i].phoneName}\t" +
				"${listPhoneBook[i].phoneNumber}"
	}
}

// main
int incount = 10;
for (int i=0; i < incount; ++i){
	insert(nameData[i], numberData[i]);
}

println "List All Inserted Entry";
listAll();

remove("Kim KS");
insert("Kim CS", 5555);
update("Kang JH", 5999);
println "Find Phone Number By Name -- Kim CS	" +
		"${findPhoneNumber("Kim CS")}";
insert("Hwan YK", 9999);
insert("Ha YL", 9998);
insert("Lee JM", 9922)
insert("Kim DS", 9997);
update("Kim DS", 9990);
println "Find Phone Number By Number -- 9998	" +
		"${findPhoneName(9998)}";
remove("Bae JM");
update("Lee DI" , 9991)
remove("Lee JM");

println "List All Updated Entry";
listAll();
