package net.dzyga.android.netronicapp.net.responses;

import com.google.gson.annotations.SerializedName;
import net.dzyga.android.netronicapp.model.User;
import java.util.List;

public class UsersResponse {

    @SerializedName("results")
    public List<User> results;
    @SerializedName("info")
    public Info info;

    public class Info{
        @SerializedName("seed")
        public String seed;
        @SerializedName("results")
        public int results;
        @SerializedName("page")
        public int page;
        @SerializedName("version")
        public String version;
    }
}
