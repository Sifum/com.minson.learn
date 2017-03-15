package com.minson.learn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class PersonTwo implements Parcelable {

    private String name;
    private  int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public static final Parcelable.Creator<PersonTwo> CREATOR = new Parcelable.Creator<PersonTwo>() {
        @Override
        public PersonTwo createFromParcel(Parcel parcel) {
            PersonTwo person = new PersonTwo();
            person.name = parcel.readString();
            person.age = parcel.readInt();
            return person;
        }

        @Override
        public PersonTwo[] newArray(int i) {
            return new PersonTwo[i];
        }
    };

}
