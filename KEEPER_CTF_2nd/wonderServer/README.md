# 이상한 서버의 엘리스

## 문제 설명

한가로이 당직을 서며 서버를 모니터링하던 엘리스는 서버실로 뛰어들어가는 토끼를 발견했습니다. 깜짝 놀란 엘리스는 토끼를 따라 서버실로 들어갔고, 거기엔 믿을 수 없는 로그들이 찍혀 있었습니다.

```
rabbitrabbirabbitrabbitrabbitrab
bitrabbitrabbitrabbirabbitrabbit
rabbrabbitrabbitrabbitrabbirabbi
trabbitrabbitrabbitrabbitrabbitr
abbirabbitrabbitrabbitrabbitrabb
itrabbitrabbirabbitrabbitrabbitr
abrabbitrabbitrabbirabbitrabbitr
abbitrabbitrrabbitrabbitrabbitra
bbitrabbit ...
```

토끼가 말하길, 토끼 세계에는 `rabbit`을 10000번 찍으면 소원이 이루어진다는 미신이 있다고 합니다.

로그 분석 장인 엘리스는 문득 소원을 이룬 토끼가 몇마리인지 궁금해졌습니다. 소원을 이룬 토끼의 수를 찾아내주세요.

N번 찍다의 기준은 다음과 같습니다.

`rabbit`: 1번 찍음

`rabbitrabbit`: 2번 찍음

`rabbitrabbitrabbit`: 3번 찍음

`rabbitrabbitrabbitrabbit`: 4번 찍음

...

`rabbit`을 10000번 찍어 소원을 이룬 토끼가 몇마리인지 맞추면 됩니다.

## 입출력

### INPUT

토끼 세계의 로그가 주어집니다.

토끼들은 꼼수를 좋아해서 누군가가 이미 10000번 적어놓은 로그에 `rabbit` 한 단어만 적어서 본인도 10000번을 채우는 식을 자주 사용합니다.

따라서 겹치는 단어도 모두 세어야 합니다.

```java
String str = "rabbitrabbit ... rabbit"; // rabbit 10000번 반복

String str2 = str + "rabbit"; // rabbit 10001번 반복

// str2에는 소원을 이룬 토끼가 총 2마리 입니다.
```

예를 들어 `aba`가 소원을 이루어주는 단어라면 `ababa`에는 소원을 이루어주는 단어가 **2개 있다고 볼 수 있습니다.**

`aba` `abababc` -> 2개

`aa` `aaaa` -> 3개

### OUTPUT

소원을 이룬 토끼의 수를 `KEEPER{}`안에 담아서 제출하시면 됩니다.

> Ex) 3133731337 -> KEEPER{3133731337}
>
> Ex) 308 -> KEEPER{308}
>
> Ex) 0 -> KEEPER{0} 

### [데이터셋 링크](https://drive.google.com/file/d/1-ALIsxaLMe1miFKQZnYAt8LiV8SJlSm9/view)