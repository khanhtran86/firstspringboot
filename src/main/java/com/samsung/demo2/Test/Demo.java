package com.samsung.demo2.Test;

public class Demo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        Box<Cat> catBox = new Box<>();
        catBox.Add(cat);


        Cat obj = catBox.Get();
        obj.Say();


        Box<Dog> dogBox = new Box<>();
        dogBox.Add(dog);

        Dog dogObj = dogBox.Get();
        dogObj.Say();
    }
}
