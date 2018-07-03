package designPattern.builder;

public class Dog {
    private String name;

    private String age;

    private String size;

    private String kind;

    private String favourateFood;

    private Dog() {
    }

    private Dog(Builder builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.size = builder.size;
        this.kind = builder.kind;
        this.favourateFood = builder.favourateFood;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", size='" + size + '\'' +
                ", kind='" + kind + '\'' +
                ", favourateFood='" + favourateFood + '\'' +
                '}';
    }

    public  static class Builder{
        private String name; //neccessary1

        private String age;//neccessary2

        private String size;//optional1

        private String kind;//optional2

        private String favourateFood;//optional3

        public Builder(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public Builder addSize(String size) {
            this.size = size;
            return this;
        }

        public Builder addKind(String kind) {
            this.kind = kind;
            return this;
        }
        public Builder addFavourateFood(String favourateFood) {
            this.favourateFood = favourateFood;
            return this;
        }

        public Dog build(){
            return new Dog(this);
        }
    }

    public static void main(String[] args) {
        Dog tom = new Dog.Builder("tom","11").addSize("100").addKind("haski").build();
        System.out.println(tom.toString());

    }
}
