# AudioRecord
public class AudioRecord
extends Object implements AudioRouting

java.lang.Object
   ↳	android.media.AudioRecord

***
AudioRecord 클래스는 하드웨어에서 오디오를 녹음하기 위해 Java 응용프로그램의 Audio resource를 관리한다. 이것은 "pulling"(reading)에 의해 데이터가 관리된다.

pulling의 방법 3가지 - 사용자의 편의에 따라 선택할 수 있음
1. read(byte[], int, int)
2. read(short[],int,int)
3. read(ByteBuffer, int)

AudioRecord 객체 생성시 새로운 Audio data를 채울 Audio Buffer를 초기화 한다. construction되는 동안 결정된느 버퍼의 사이즈는 "over-run"되기까지의 시간을 결정한다.
