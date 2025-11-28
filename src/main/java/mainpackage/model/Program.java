package mainpackage.model;

public class Program {
    private int id;
    private int voiceTime;
    private int sms;
    private int data;
    private float price;
    private int extraVoice;
    private int extraSms;
    private int extraData;

    
    public Program() {
    }

    
    public Program(int voiceTime, int sms, int data,
                   float price, int extraVoice,
                   int extraSms, int extraData) {
        this.voiceTime   = voiceTime;
        this.sms         = sms;
        this.data        = data;
        this.price       = price;
        this.extraVoice  = extraVoice;
        this.extraSms    = extraSms;
        this.extraData   = extraData;
    }

    public Program(int id, int voiceTime, int sms, int data,
                   float price, int extraVoice,
                   int extraSms, int extraData) {
        this.id          = id;
        this.voiceTime   = voiceTime;
        this.sms         = sms;
        this.data        = data;
        this.price       = price;
        this.extraVoice  = extraVoice;
        this.extraSms    = extraSms;
        this.extraData   = extraData;
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVoiceTime() { return voiceTime; }
    public void setVoiceTime(int voiceTime) { this.voiceTime = voiceTime; }

    public int getSms() { return sms; }
    public void setSms(int sms) { this.sms = sms; }

    public int getData() { return data; }
    public void setData(int data) { this.data = data; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getExtraVoice() { return extraVoice; }
    public void setExtraVoice(int extraVoice) { this.extraVoice = extraVoice; }

    public int getExtraSms() { return extraSms; }
    public void setExtraSms(int extraSms) { this.extraSms = extraSms; }

    public int getExtraData() { return extraData; }
    public void setExtraData(int extraData) { this.extraData = extraData; }
}


