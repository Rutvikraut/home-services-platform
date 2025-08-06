namespace Home_Service_Admin.Services
{
    public class SpringBootApiService
    {

        private readonly HttpClient _httpClient;
        //private IConfiguration _configuration;

        public SpringBootApiService(HttpClient httpClient)
        {
            _httpClient = httpClient;
            //_configuration = configuration;
        }
        public async Task<string> GetTestAsync()
        {
            String url = "http://localhost:8080/test";
            //using var client = new HttpClient();
            Console.WriteLine("SpringBootApiService: " + url);
            var response = await _httpClient.GetAsync(url);
            Console.WriteLine("Response: " + response.StatusCode);
            string body = await response.Content.ReadAsStringAsync();
            Console.WriteLine("Response Body: " + body);

            response.EnsureSuccessStatusCode();
            return body;
        }

        public async Task<string> GetAllOrderAsync()
        {
            string url = "http://localhost:8080/order";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }

        //Get All Categories
        public async Task<string> GetAllCategoriesAsync()
        {
            string url = "http://localhost:8080/categories";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }

        //Get All Services
        public async Task<string> GetAllServiceAsync()
        {
            string url = "http://localhost:8080/service";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }

        //Verify Partner
        public async Task<string> VerifyPartnerAsync(int id)
        {
            string url = $"http://localhost:8080/partner/{id}/verify";
            var content = new StringContent("");
            var response = await _httpClient.PutAsync(url, content);
            var responseContent = await response.Content.ReadAsStringAsync();
            if (!response.IsSuccessStatusCode)
            {
                throw new HttpRequestException($"Spring Boot error {response.StatusCode}: {responseContent}");
            }
            return responseContent;
        }


        //Get Partner By Id
        public async Task<string> GetPartnerByIdAsync(int id)
        {
            string url = $"http://localhost:8080/partner/{id}";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }


        //Baadme
        //Get All Partners
        public async Task<string> GetAllPartnerAsync()
        {
            string url = "http://localhost:8080/partner";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }

        public async Task<string> GetAllVerifiedPartnerAsync()
        {
            string url = "http://localhost:8080/partner/verified";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }

        // get Unverified partner
        public async Task<string> GetAllUnverfiedPartnerAsync()
        {
            string url = "http://localhost:8080/partner/unverified";
            var response = await _httpClient.GetAsync(url);
            string body = await response.Content.ReadAsStringAsync();
            response.EnsureSuccessStatusCode();
            return body;
        }


        //Assign Partners(End)
        public async Task<string> AssignOrderToPartner(long partnerId, long orderId)
        {
            Console.WriteLine(partnerId.ToString(), orderId);
            string url = $"http://localhost:8080/partner/{partnerId}/orders/{orderId}";
            var response = await _httpClient.PutAsync(url, null); 
            response.EnsureSuccessStatusCode();
            return await response.Content.ReadAsStringAsync();
        }
        //Restrict Partner

    }
}
