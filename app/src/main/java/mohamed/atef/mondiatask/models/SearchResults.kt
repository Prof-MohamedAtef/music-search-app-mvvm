package mohamed.atef.mondiatask.models

import android.os.Parcel
import android.os.Parcelable

data class SearchResults(val Image:String,val Title: String, val Type:String,val ArtistName:String) :Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.Image)
        dest?.writeString(this.Title)
        dest?.writeString(this.Type)
        dest?.writeString(this.ArtistName)
    }

    companion object CREATOR : Parcelable.Creator<SearchResults> {
        override fun createFromParcel(parcel: Parcel): SearchResults {
            return SearchResults(parcel)
        }

        override fun newArray(size: Int): Array<SearchResults?> {
            return arrayOfNulls(size)
        }
    }
}