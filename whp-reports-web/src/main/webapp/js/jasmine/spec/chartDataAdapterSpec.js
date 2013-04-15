describe("extractData", function() {

    it("should convert data", function() {
        var results =
            [{district : "district1", count : 12},
                {district : "district2", count : 13}]

        var data = extractData(results, "district", "count");

        expect(data.xAxis).toEqual(["district1", "district2"]);
        expect(data.yAxis).toEqual([12, 13]);
    });

})