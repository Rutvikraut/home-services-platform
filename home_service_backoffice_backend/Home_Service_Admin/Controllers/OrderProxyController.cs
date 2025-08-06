using Home_Service_Admin.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Home_Service_Admin.Controllers
{
    [ApiController]
    [Route("api/admin/orders")]
    public class OrderProxyController : ControllerBase
    {
        private readonly SpringBootApiService _springService;
        public OrderProxyController(SpringBootApiService springService)
        {
            _springService = springService;
        }

        [HttpGet("test")]
   
        public async Task<IActionResult> GetAllTasks()
        {
            var data = await _springService.GetTestAsync();
            return Ok(data);
        }

        [HttpGet("orders")]
        public async Task<IActionResult> GetAllOrders()
        {
            var result = await _springService.GetAllOrderAsync();
            return Content(result, "application/json");
        }

        [HttpGet("categories")]
        public async Task<IActionResult> GetAllCategories()
        {
            var result = await _springService.GetAllCategoriesAsync();
            return Content(result, "application/json");
        }

        [HttpGet("service")]
        public async Task<IActionResult> GetAllService()
        {
            var result = await _springService.GetAllServiceAsync();
            return Content(result, "application/json");
        }

        [HttpGet("partner/{id}")]
        public async Task<IActionResult> GetPartnerById(int id)
        {
            var result = await _springService.GetPartnerByIdAsync(id);
            return Content(result, "application/json");
        }

        [HttpPut("partner/{id}/verify")]
        public async Task<IActionResult> VerifyPartner(int id)
        {
            var result = await _springService.VerifyPartnerAsync(id);
            return Content(result, "application/json");
        }

        [HttpGet("partner/verified")]
        public async Task<IActionResult> GetAllVerifiedPartner()
        {
            var result = await _springService.GetAllVerifiedPartnerAsync();
            return Content(result, "application/json");
        }

        [HttpGet("partner/unverified")]
        public async Task<IActionResult> GetAllUnverifiedPartner()
        {
            var result = await _springService.GetAllUnverfiedPartnerAsync();
            return Content(result, "application/json");
        }

        [HttpGet("partner")]
        public async Task<IActionResult> GetAllPartner()
        {
            var result = await _springService.GetAllPartnerAsync();
            return Content(result, "application/json");
        }

        [HttpPut("partner/{partnerId}/orders/{orderId}")]
        public async Task<IActionResult> AssignOrderToPartner(long partnerId, long orderId)
        {
            var result = await _springService.AssignOrderToPartner(partnerId, orderId);
            return Content(result, "application/json");
        }

    }

}
