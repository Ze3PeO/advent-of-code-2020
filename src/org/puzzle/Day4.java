package org.puzzle;

import org.util.IO;

import java.util.List;
import java.util.Map;


public class Day4 implements PuzzleInterface{
    @Override
    public void start() {
        System.out.println("---Puzzle Day 4---");
        List<Map<String, String>> maps = IO.readMapsFromRessource("input/day4_input.txt");
        for(Map<String, String> map : maps){
            System.out.println("map = " + map);
        }

        System.out.println("1)");
        int result1 = p1(maps);
        System.out.println("result1 = " + result1);

        System.out.println("2)");
        int result2 = p2(maps);
        System.out.println("result2 = " + result2);
    }

    private int p1(List<Map<String, String>> maps) {
        int validPassports = 0;

        for(Map<String, String> map : maps){
            if(map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") &&
                    map.containsKey("hgt") && map.containsKey("hcl") && map.containsKey("ecl") &&
                    map.containsKey("pid")){
                validPassports++;
            }
        }

        return validPassports;
    }

    private int p2(List<Map<String, String>> maps) {
        int validPassports = 0;

        for(Map<String, String> map : maps){
            int flag = 0;
            if(!(map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") &&
                    map.containsKey("hgt") && map.containsKey("hcl") && map.containsKey("ecl") &&
                    map.containsKey("pid"))){
                flag++;
            }else{
                try{
                    int byr = Integer.parseInt(map.get("byr"));
                    int iyr = Integer.parseInt(map.get("iyr"));
                    int eyr = Integer.parseInt(map.get("eyr"));
                    String hgt = map.get("hgt");
                    String hcl = map.get("hcl");
                    String ecl = map.get("ecl");
                    String pid = map.get("pid");

                    if(byr < 1920 || byr > 2002){
                        flag++;
                    }
                    if(iyr < 2010 || iyr > 2020){
                        flag++;
                    }
                    if(eyr < 2020 || eyr > 2030){
                        flag++;
                    }
                    if(hgt.contains("cm")){
                        int height = Integer.parseInt(hgt.replace("cm", ""));
                        if(height < 150 || height > 193){
                            flag++;
                        }
                    } else if(hgt.contains("in")){
                        int height = Integer.parseInt(hgt.replace("in", ""));
                        if(height < 59 || height > 76){
                            flag++;
                        }
                    }else{
                        flag++;
                    }
                    if(hcl.charAt(0) != '#' && hcl.length() != 7){
                        flag++;
                    }
                    if(!ecl.contains("amb") && !ecl.contains("blu") && !ecl.contains("brn") &&
                            !ecl.contains("gry") && !ecl.contains("grn") && !ecl.contains("hzl") &&
                            !ecl.contains("oth")){
                        flag++;
                    }
                    if(pid.length() != 9){
                        flag++;
                    }
                }catch (Exception e){
                    flag++;
                }
            }
            if(flag == 0){
                validPassports++;
            }
        }

        return validPassports;
    }
}
