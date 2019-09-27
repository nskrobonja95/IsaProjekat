<div style="padding-left:50px">
    <form>
        <div class="form-group">
            <label for="serviceName">Name:</label>
            <input type="text" class="form-control" ng-model="addServiceCtrl.name" id="serviceName">
        </div>
        <div class="form-group">
            <label for="rate">Rate($):</label>
            <input type="number" class="form-control" ng-model="addServiceCtrl.rate" id="rate">
        </div>
        <div class="form-group">
            <label for="charge">Charge:</label>
            <select id="charge" ng-model="addServiceCtrl.charge"
                class="form-control">
                <option value="per stay" selected>Per stay</option>
                <option value="per night">Per night</option>
            </select>
        </div>
        <button class="btn btn-info" ng-click="addServiceCtrl.addService()">Save</button>
    </form>
</div>