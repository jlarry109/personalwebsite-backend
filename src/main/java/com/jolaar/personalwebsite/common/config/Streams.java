package com.jolaar.personalwebsite.common.config;

import com.jolaar.personalwebsite.model.Skill;
import com.jolaar.personalwebsite.repository.SkillRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String [] args){
        List<Integer> integerList = List.of(1, 12, 23, 209, 39,0);
        List<Integer> streamedInteger = integerList.stream().map(a -> 3*a + 1)
                .filter(a -> a > 80)
                .filter(a -> a % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
        for (Integer i = 0; i < streamedInteger.size(); i++) {
            System.out.println(i + " " + streamedInteger.get(i));
        }
        DoubleFunc doubleFunction = (int a) -> a * 2;
        FuncAdd addFundtion = (int a, int b) -> a + b;
        SafeDivide safeDivision = (int a, int b) -> {
            if (b == 0) {
                return 0;
            }
            return a / b;
        };
        StrLen stringLengthCountFunction = (String s) -> s.length();
        System.out.println(doubleFunction.d(13));
        System.out.println(addFundtion.addNumber(13, 19));
        System.out.println(safeDivision.safeDiv(9, 13));
        System.out.println(stringLengthCountFunction.strlen("What is the length of this string?"));
    }
    @FunctionalInterface
    public interface DoubleFunc {
        public int d (int a);
    }
    @FunctionalInterface
    public interface FuncAdd {
        int addNumber(int a, int b);
    }

    @FunctionalInterface
    public interface SafeDivide{
        double safeDiv(int a, int b);
    }
@FunctionalInterface
    public interface StrLen{
        int strlen(String s);
    }
}
