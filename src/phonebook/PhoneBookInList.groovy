package phonebook


import java.util.List

import groovy.transform.Field


@Field def nameData = ["Park SH", "Kang JH",
	"Kim KS", "Lee YH", "Kang SH","Bae JM",
	"Lee DI", "Lee BH", "Jang WH", "Chun WY"];
@Field def numberData = [5023, 5002, 5008,
	5067, 5038, 5381, 5125, 5165, 5684, 5752];

@Field def List<Entry> listPhoneBook = []


//이름이 어디에 있는 지 찾기
int findLoc(String name){
	for (int i=0; i<listPhoneBook.size(); ++i){
		if (listPhoneBook[i].phoneName == name)
			return i;
	}
	return -1; // not found
}

//번호가 어디에 위치하는 지 확인
int findLoc2(int number){
	for (int i=0; i<listPhoneBook.size(); ++i){
		if (listPhoneBook[i].phoneNumber.equals(number))
			return i;
	}
	return -1; // not found
}

//번호로부터 이름을 알아냄
String findPhoneName(int number){
	int loc = findLoc2(number);
	if (loc == -1)
		return null; // not found
	return listPhoneBook[loc].phoneName;
}

//이름으로부터 번호를 알아냄
int findPhoneNumber(String name){
	int loc = findLoc(name);
	if (loc == -1) {
		println "***Error -- Not found";
		return -1; // not found
	}
	return listPhoneBook[loc].phoneNumber;
}

//주소록에 같은 이름이 없을 시 추가한다.
void insert(String name, int number){
	int loc = findLoc(name);
	if (loc == -1){ // insert
		listPhoneBook.add(new Entry(name,number))	
	}
	else {
		println "***Error -- Duplicated Name";
	}
}

//주소록에 이름이 있을 경우 제거한다.
void remove(String name){
	int loc = findLoc(name);
	if (loc != -1){ // remove
		listPhoneBook.remove(loc);
	}
	else {
		println "***Error -- Name not found";
	}
}

//주소록에 이름이 있을 경우 번호를 바꾼다.
void update(String name, int number){
	int loc = findLoc(name);
	if(loc != -1) {
		listPhoneBook[loc].phoneNumber = number;
	} else {
		println "***Error -- Name not found";
	}
}

//주소록의 모든 사람과 번호를 출력
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
