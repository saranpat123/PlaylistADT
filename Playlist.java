import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Playlist — ADT แทนรายการเพลงที่ผู้ใช้จัดลำดับไว้
 *
 * ค่านามธรรม (A): ลำดับของเพลง เช่น [เพลงA, เพลงB, เพลงC]
 *
 * ตัวอย่างการใช้งาน:
 *     Playlist p = new Playlist();
 *     p.add("Bohemian Rhapsody");
 *     p.add("Imagine");
 *     System.out.println(p.size());   // 2
 */
public class Playlist {

    public static final int MAX_SONGS = 100;

    // ===== representation =====
    private final List<String> songs;

    // TODO 1: เขียน Abstraction Function ตรงนี้
    // Abstraction Function:
    // AF(songs) = เพลย์ลิสต์ที่เล่นเพลง songs.get(0), songs.get(1), ...,
    // songs.get(songs.size()-1) ตามลำดับ โดยเพลงแรกคือเพลงที่เล่นก่อน

    // TODO 2: เขียน Representation Invariant ตรงนี้
    // Representation Invariant:
    // 1. songs ต้องไม่เป็น null
    // 2. ไม่มีสมาชิกใน songs เป็น null
    // 3. ไม่มีสมาชิกใน songs เป็นสตริงว่าง
    // 4. ไม่มีชื่อเพลงซ้ำกัน
    // 5. จำนวนเพลงไม่เกิน MAX_SONGS

    // TODO 3: เขียน Safety from rep exposure ตรงนี้
    // Safety from rep exposure:
    // 1. songs เป็น private และ final
    // 2. Constructor คัดลอกข้อมูลด้วย new ArrayList<>(initial)
    // 3. songs() คืน defensive copy ด้วย new ArrayList<>(songs)

    /**
     * TODO 4: เขียน checkRep()
     */
    private void checkRep() {
        assert songs != null : "songs must not be null";
        assert songs.size() <= MAX_SONGS : "too many songs";

        Set<String> seen = new HashSet<>();

        for (String s : songs) {
            assert s != null : "song is null";
            assert !s.isEmpty() : "song is empty";
            assert seen.add(s) : "duplicate song: " + s;
        }
    }

    // ===== Creator =====

    /**
     * สร้างเพลย์ลิสต์ว่าง
     */
    public Playlist() {
        this.songs = new ArrayList<>();
        checkRep();
    }

    /**
     * TODO 5: Creator ตัวที่สอง
     *
     * @param initial รายชื่อเพลงเริ่มต้น
     */
    public Playlist(List<String> initial) {

        if (initial == null)
            throw new IllegalArgumentException();

        if (initial.size() > MAX_SONGS)
            throw new IllegalArgumentException();

        Set<String> seen = new HashSet<>();

        for (String s : initial) {

            if (s == null || s.isEmpty())
                throw new IllegalArgumentException();

            if (!seen.add(s))
                throw new IllegalArgumentException();
        }

        this.songs = new ArrayList<>(initial);

        checkRep();
    }

    // ===== Mutators =====

    /**
     * TODO 6: เพิ่มเพลงต่อท้ายเพลย์ลิสต์
     */
    public boolean add(String song) {

        if (song == null || song.isEmpty())
            throw new IllegalArgumentException();

        if (songs.contains(song))
            return false;

        if (songs.size() >= MAX_SONGS)
            return false;

        songs.add(song);

        checkRep();

        return true;
    }

    /**
     * TODO 7: ลบเพลงออกจากเพลย์ลิสต์
     */
    public boolean remove(String song) {

        boolean result = songs.remove(song);

        checkRep();

        return result;
    }

    // ===== Observers =====

    /**
     * TODO 8: คืนจำนวนเพลงในเพลย์ลิสต์
     */
    public int size() {
        return songs.size();
    }

    /**
     * TODO 9: ตรวจว่ามีเพลงนี้อยู่หรือไม่
     */
    public boolean contains(String song) {
        return songs.contains(song);
    }

    /**
     * TODO 10: คืนรายชื่อเพลงทั้งหมดตามลำดับ
     */
    public List<String> songs() {
        return new ArrayList<>(songs);
    }

    // ===== Producer =====

    /**
     * TODO 11: คืนเพลย์ลิสต์ใหม่ที่มีเพลงเดียวกันแต่สลับลำดับ
     */
    public Playlist shuffled() {
        List<String> copy = new ArrayList<>(songs);
        Collections.shuffle(copy);
        return new Playlist(copy);
    }

    @Override
    public String toString() {
        return songs.toString();
    }
}