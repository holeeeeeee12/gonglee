package com.example.gonglee;

import com.example.gonglee.entity.Question;
import com.example.gonglee.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class GongleeApplication {

    public static void main(String[] args) {
        // 기존 코드의 오타 수정 (GonglassApplication -> GongleeApplication)
        SpringApplication.run(GongleeApplication.class, args);
    }

    /**
     * 애플리케이션 시작 시 실행되어 임시 A/B 테스트 데이터를 생성합니다.
     * Question 엔티티가 com.example.gonglee.entity 패키지에 있다고 가정합니다.
     * @param questionRepository Question 엔티티를 관리하는 리포지토리
     * @return CommandLineRunner Bean
     */
    @Bean
    public CommandLineRunner dataLoader(QuestionRepository questionRepository) {
        return args -> {
            // 기존 데이터가 없으면 임시 데이터를 생성합니다.
            if (questionRepository.count() == 0) {
                Random random = new Random();

                // 1. 소주 vs 맥주 A/B 테스트 질문 생성
                Question sojuVsBeer = new Question();
                sojuVsBeer.setTitle("🍻 당신의 선택은? 소주 vs 맥주");
                sojuVsBeer.setOptionA("소주 (깔끔하게)");
                sojuVsBeer.setOptionB("맥주 (배부르게)");
                setRandomVotes(sojuVsBeer, 50, 100, random); // 헬퍼 메서드 사용
                questionRepository.save(sojuVsBeer);

                // 2. 커피 vs 차 질문 생성
                Question coffeeVsTea = new Question();
                coffeeVsTea.setTitle("☕ 하루의 시작: 커피 vs 차");
                coffeeVsTea.setOptionA("아침을 깨우는 모닝 커피");
                coffeeVsTea.setOptionB("아침부터 나른한 차");
                setRandomVotes(coffeeVsTea, 100, 150, random); // 헬퍼 메서드 사용
                questionRepository.save(coffeeVsTea);

                // 3. 고기 종류: 돼지 vs 소 (추가)
                Question meatVsMeat = new Question();
                meatVsMeat.setTitle("🥩 맛있는 고기 종류: 돼지 vs 소");
                meatVsMeat.setOptionA("삼겹살, 목살 (돼지)");
                meatVsMeat.setOptionB("등심, 안심 (소)");
                setRandomVotes(meatVsMeat, 70, 120, random);
                questionRepository.save(meatVsMeat);

                // 4. 중국집 면 종류: 짜장면 vs 짬뽕 (추가)
                Question noodleVsNoodle = new Question();
                noodleVsNoodle.setTitle("🍜 중국집 최강 면 요리: 짜장면 vs 짬뽕");
                noodleVsNoodle.setOptionA("달콤한 짜장면");
                noodleVsNoodle.setOptionB("건더기 없는 짬뽕");
                setRandomVotes(noodleVsNoodle, 120, 180, random);
                questionRepository.save(noodleVsNoodle);

                // 5. 날씨: 엄청 더운 여름 vs 엄청 추운 겨울 (추가)
                Question weatherVsWeather = new Question();
                weatherVsWeather.setTitle("☀️🌡️ 날씨 선호도: 더운 여름 vs 추운 겨울");
                weatherVsWeather.setOptionA("엄청 더우우운 여름");
                weatherVsWeather.setOptionB("엄청 추우우운 겨울");
                setRandomVotes(weatherVsWeather, 40, 80, random);
                questionRepository.save(weatherVsWeather);

                System.out.println("✨ 임시 A/B 테스트 질문 5개가 데이터베이스에 저장되었습니다.");
            } else {
                System.out.println("데이터베이스에 이미 질문이 존재하여 임시 데이터 생성기를 건너뜁니다.");
            }
        };
    }

    /**
     * 질문 객체에 최소/최대 투표 범위 내에서 무작위 투표 수를 설정하는 헬퍼 메서드.
     * 이 메서드가 없으면 빨간불 오류가 발생합니다.
     */
    private void setRandomVotes(Question question, int minTotal, int maxTotal, Random random) {
        // maxTotal 범위에 minTotal이 포함되도록 조정합니다.
        int totalVotes = minTotal + random.nextInt(maxTotal - minTotal + 1);
        int countA = random.nextInt(totalVotes + 1);
        int countB = totalVotes - countA;

        // Question 엔티티에 public setter (setCountA, setCountB)가 정의되어 있어야 합니다.
        question.setCountA(countA);
        question.setCountB(countB);
    }
}