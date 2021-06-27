
import com.thoughtworks.gauge.Step;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.*;



public class StepImplementation extends BaseTest{



    @Step("Board Olustur")
    public void createBoard() {

        HttpResponse<String> response = Unirest.post(URL+"boards/?")
                .queryString("key", KEY)
                .queryString("token", TOKEN)
                .queryString("name","Create Board")
                .asString();

        String jsonString = response.getBody();
        JSONObject obj = new JSONObject(jsonString);
        System.out.println("ID:"+obj.get("id"));
        id= (String) obj.get("id");
        boardName=(String)obj.get("name");

    }

    @Step("Liste olustur")
    public void createList(){

        HttpResponse<String> response1 = Unirest.post(URL+"/lists")
                .queryString("key", KEY)
                .queryString("token", TOKEN)
                .queryString("name",boardName)
                .queryString("idBoard", id)
                .asString();


        String jsonString = response1.getBody();
        JSONObject obj = new JSONObject(jsonString);
        list_id= (String) obj.get("id");

    }


    @Step("<name>Card Ekle")
    public void createCard(String name){

        HttpResponse<String> response = Unirest.post(URL+"cards?")
                .queryString("key", KEY)
                .queryString("token", TOKEN)
                .queryString("idList",list_id)
                .queryString("name",name)
                .asString();

    }

    @Step("Board sil")
    public void deleteBoard(){

        HttpResponse<String> response = Unirest.delete(URL+"boards/"+id+"?")
                .queryString("key", KEY)
                .queryString("token", TOKEN)
                .asString();

    }

    @Step({"<seconds> saniye bekle"})
    public void waitBySecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
