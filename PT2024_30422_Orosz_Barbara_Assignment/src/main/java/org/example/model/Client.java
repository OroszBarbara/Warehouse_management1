package org.example.model;
/**
 * <p>Model class for an entry in  the 'Client' table</p>
 */
public class Client {

        private int id;
        private String name;
        private String address;
        private int age;
        private String email;

    /**
     * <p>constructor without parameters</p>
     */
        public Client() { }

    /**
     * <p>Constructor with parameters</p>
     * @param name client name
     * @param address client address
     * @param age client age
     * @param email client email
     */
        public Client(String name, String address, int age, String email) {
            this.name = name;
            this.address = address;
            this.age = age;
            this.email = email;
        }

    /**
     * <p>Constructor with parameters</p>
     * @param id client id
     * @param name client name
     * @param address client address
     * @param age client age
     * @param email client email
     */
        public Client(int id, String name, String address, int age, String email) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.age = age;
            this.email = email;
        }


    /**
     * <p>Getter for name</p>
     * @return name of client
     */
        public String getName() {
            return name;
        }

    /**
     * <p>Setter for name</p>
     * @param name new name of client
     */
        public void setName(String name) {
            this.name = name;
        }

    /**
     * <p>Getter for address</p>
     * @return address of client
     */
        public String getAddress() {
            return address;
        }

    /**
     * <p>Setter for address</p>
     * @param address new address of client
     */
        public void setAddress(String address) {
            this.address = address;
        }
    /**
     * <p>Getter for age</p>
     * @return age of client
     */
        public int getAge() {
            return age;
        }

    /**
     * <p>Setter for age</p>
     * @param age new age of client
     */
        public void setAge(int age) {
            this.age = age;
        }

    /**
     * <p>Getter for email</p>
     * @return email of client
     */
        public String getEmail() {
            return email;
        }

    /**
     * <p>Setter for email</p>
     * @param email new email of client
     */
        public void setEmail(String email) {
            this.email = email;
        }
    /**
     * <p>Getter for id</p>
     * @return id of client
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Setter for id</p>
     * @param id new id of client
     */
    public void setId(int id) {
        this.id = id;
    }
}
