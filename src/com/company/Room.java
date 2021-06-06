package com.company;

class Room{
    private int room;
    private String firstname;
    private String surname;
    private int creditNumber;
    private int guestCount;

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public void setRoom(int room){
        this.room = room;
    }

    public int getRoom(){
        return room;
    }




    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }


    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return surname;
    }

    public void setCreditNumber(int a) {
        this.creditNumber = a;
    }

    public int getCreditNumber(){
        return creditNumber;
    }
}


