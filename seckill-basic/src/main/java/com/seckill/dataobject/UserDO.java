package com.seckill.dataobject;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.gender
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.age
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.phone
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.register_type
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private String registerType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.third_party_id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    private String thirdPartyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.gender
     *
     * @return the value of user_info.gender
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.gender
     *
     * @param gender the value for user_info.gender
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.age
     *
     * @return the value of user_info.age
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.age
     *
     * @param age the value for user_info.age
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.phone
     *
     * @return the value of user_info.phone
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.phone
     *
     * @param phone the value for user_info.phone
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.register_type
     *
     * @return the value of user_info.register_type
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public String getRegisterType() {
        return registerType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.register_type
     *
     * @param registerType the value for user_info.register_type
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setRegisterType(String registerType) {
        this.registerType = registerType == null ? null : registerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.third_party_id
     *
     * @return the value of user_info.third_party_id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public String getThirdPartyId() {
        return thirdPartyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.third_party_id
     *
     * @param thirdPartyId the value for user_info.third_party_id
     *
     * @mbg.generated Sat Jul 20 08:57:55 CST 2019
     */
    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}