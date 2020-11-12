package com.example.smartandgreensociety;



import java.util.HashMap;

import java.util.Map;

public class Society {

   private String societyId;//Firestoreid
   private String societyName;
   private String societyCode;//Secretary given
   private Map<String,Object> societyMap = new HashMap<>();

   public Society(){

   }

   public String getSocietyId() {
      return societyId;
   }

   public void setSocietyId(String societyId) {
      this.societyId = societyId;
   }

   public String getSocietyName() {
      return societyName;
   }

   public void setSocietyName(String societyName) {
      this.societyName = societyName;
   }

   public String getSocietyRef() {
      return societyCode;
   }

   public void setSocietyRef(String societyRef) {
      this.societyCode = societyRef;
   }

   public Map toMap(String societyName, String societyCode){

      societyMap.put("societyName",societyName);
      societyMap.put("societyCode",societyCode);
      return societyMap;

   }
}
