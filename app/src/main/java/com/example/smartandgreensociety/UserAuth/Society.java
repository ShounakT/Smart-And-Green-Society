package com.example.smartandgreensociety.UserAuth;



import java.util.HashMap;

import java.util.Map;

public class Society {

   private String societyRef;//Firestore provided society id
   private String societyName;
   private Map<String,Object> societyMap = new HashMap<>();

   public Society(){

   }

   public String getSocietyName() {
      return societyName;
   }

   public void setSocietyName(String societyName) {
      this.societyName = societyName;
   }

   public String getSocietyRef() {
//      return "L8TKL1L3VUn0rg6SiBUm";
      return societyRef;
   }

   public void setSocietyRef(String societyRef) {
      this.societyRef = societyRef;
   }

   public Map toMapSociety(){

      societyMap.put("societyName",this.societyName);
      societyMap.put("societyRef",this.societyRef);
      return societyMap;

   }
}
