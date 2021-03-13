package com.example.shehacks;

public class MyUser {
    public static final String PARENT = "parent";
    String uid,name,age,address,zipCode,expectation,adhaar,emailId,hrs,type;

    public static class Builder {
        MyUser myUser;
        public Builder() {
            myUser=new MyUser();
        }

        public MyUser build() {
            return myUser;
        }

        public Builder setName(String name) {
            myUser.name=name;
            return this;
        }

        public Builder setEmail(String email) {
            myUser.emailId=email;
            return this;
        }

        public Builder setAge(String age) {
            myUser.age=age;
            return this;
        }

        public Builder setAddress(String address) {
            myUser.address=address;
            return this;
        }

        public Builder setExpectation(String expectation) {
            myUser.expectation=expectation;
            return this;
        }

        public Builder setAdhaar(String adhaar) {
            myUser.adhaar=adhaar;
            return this;
        }

        public Builder setServiceHours(String hrs) {
            myUser.hrs=hrs;
            return this;
        }

        public Builder setAccountType(String type) {
            myUser.type=type;
            return this;
        }

        public Builder setUid(String uid) {
            myUser.uid=uid;
            return this;
        }

        public Builder setZipCode(String zipCode) {
            myUser.zipCode=zipCode;
            return this;
        }
    }
}
