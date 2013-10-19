package reprotool.dmodel.extensions

import org.junit.Assert
import org.junit.Test

import static extension reprotool.dmodel.extensions.StatisticalExtensions.*

class StatisticalExtensions {
	
	// same as STDEV in Calc (assumes normal distribution)
	def static stdev(Iterable<Double> population) {
		population.variance.stdev
	}
	
	// same as STDEVP in Calc (assumes normal distribution)
	def static stdevp(Iterable<Double> population, double populationMean) {
		population.variancep(populationMean).stdev
	}

	// same as STDEVP in Calc (assumes normal distribution)
	def static stdevp(Iterable<Double> population) {
		population.variancep.stdev
	}
	
	// same as STDEV in Calc (assumes normal distribution)
	def static stdev(double variance) {
		Math.sqrt(variance)
	}
	
	// same as VAR in Calc
	def static variance(Iterable<Double> population) {
		population.variance(population.mean)
	}

	// same as VAR in Calc
	def static variance(Iterable<Double> population, double populationMean) {
		if(population.size < 2)
			return Double.NaN
			
		population.map[ (it - populationMean)**2 ].sum / (population.size - 1)
	}
	
	// same as VARP in Calc
	def static variancep(Iterable<Double> population) {
		population.variancep(population.mean)
	}
	
	// same as VARP in Calc
	def static variancep(Iterable<Double> population, double populationMean) {
		population.map[ (it - populationMean)**2 ].mean
	}
	
	def static median(Iterable<Double> list) {
		if(list.size < 1) return Double.NaN
		list.sort.get(list.size / 2)
	}

	def static lowerQuartile(Iterable<Double> list) {
		list.sort.subList(0, list.size / 4).median
	}

	def static upperQuartile(Iterable<Double> list) {
		list.sort.subList( list.size - list.size/4, list.size).median
	}

	// same as SUM in Calc
	def static sum(Iterable<Integer> list, int initial) {
		list.reduce[ a, b | a + b] + initial
	}

	// same as SUM in Calc
	def static sum(Iterable<Double> list) {
		list.reduce[ a, b | a + b]
	}
		
	// same as AVERAGE in Calc
	def static mean(Iterable<Double> list) {
		if(list.size < 1)
			return Double.NaN
			
		return list.sum / list.size
	}
	
	// list of integers to doubles
	def static toDoubles(Iterable<Integer> list) {
		list.map[it as double]
	}
	
	// same as CONFIDENCE with alpha = 95% (assumes normal distribution)
	def static confidence(Iterable<Double> list) {
		confidence(list, 0.05)
	}
	
	// same as CONFIDENCE in Calc (assumes normal distribution)
	def static confidence(Iterable<Double> list, double alpha) {
		confidence(alpha, list.stdev, list.size)
	}
	
	// same as CONFIDENCE in Calc (assumes normal distribution)
	def static confidence(double alpha, double stdev, int size) {
		if(size == 0)
			return Double.NaN
			
		val z = CDFStatUtil.getInvCDF(1-alpha/2, false)
		return z * stdev / Math.sqrt(size)
	}
	
	def static filterNaN(Iterable<Double> list) {
		list.filter[ ! naN ]
	}
	
	def static convertNaNtoValue(Iterable<Double> list, double value) {
		list.map[if(naN) value else it]
	}
	
	def static nanToZero(Iterable<Double> list) {
		list.map[if(naN) 0d else it]
	}
	
	def static maxValue(Iterable<Double> list) {
		var double maxItem = Double.MIN_VALUE
		for(item : list) {
			if(item > maxItem) maxItem = item
		}
		return maxItem
	}

	def static minValue(Iterable<Double> list) {
		var double minItem = Double.MAX_VALUE
		for(item : list) {
			if(item < minItem) minItem = item
		}
		return minItem
	}
}

class StatisticalExtensionsTest {
	@Test def void testSum() {
		Assert.assertEquals(55, (0..10).toDoubles.sum, 0)
	}
	
	@Test def void testMean() {
		Assert.assertEquals(5, (0..10).toDoubles.mean, 0)
	}
	
	@Test def void testVariance() {
		Assert.assertEquals(5, (0..10).toDoubles.mean, 0)
	}
	
	@Test def void testStdev() {
		val data = newArrayList(86,37,66,91,0,83,15,94,97,99).toDoubles
		Assert.assertEquals( 34.5537262824, data.stdevp, 0.1 )
		Assert.assertEquals( 36.4228255662, data.stdev, 0.1 )
	}
	
	@Test def void testConfidence() {
		Assert.assertEquals(0.0638106839, confidence(0.05, 0.102954494, 10), 0.0001)
	}
	
	@Test def void testMedian() {
		Assert.assertEquals("Empty list", Double.NaN, newArrayList().median, 0)
		Assert.assertEquals("Single value", 1d, newArrayList(1d).median, 0)
		Assert.assertEquals(2d, newArrayList(1d,2d).median, 0)
		Assert.assertEquals(4d, newArrayList(1d,2d,3d,4d,5d,6d).median, 0)
		Assert.assertEquals(3d, newArrayList(1d,2d,3d,4d,5d).median, 0)
		Assert.assertEquals("Unordered input", 3d, newArrayList(5d,2d,1d,3d,4d).median, 0)
		Assert.assertEquals("Unordered input", 4d, newArrayList(1d,2d,3d,4d,5d,6d).median, 0)
	}
	
	@Test def void testQuartiles() {
		Assert.assertEquals(2d,  newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d,11d,12d).lowerQuartile, 0)
		Assert.assertEquals(11d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d,11d,12d).upperQuartile, 0)

		Assert.assertEquals(2d,  newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d,11d).lowerQuartile, 0)
		Assert.assertEquals(11d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d,11d).upperQuartile, 0)

		Assert.assertEquals(2d,  newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d).lowerQuartile, 0)
		Assert.assertEquals(10d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d).upperQuartile, 0)

		Assert.assertEquals(2d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d).lowerQuartile, 0)
		Assert.assertEquals(9d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d,9d).upperQuartile, 0)

		Assert.assertEquals(2d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d).lowerQuartile, 0)
		Assert.assertEquals(8d, newArrayList(1d,2d,3d,4d,5d,6d,7d,8d).upperQuartile, 0)

		Assert.assertEquals(1d, newArrayList(1d,2d,3d,4d,5d,6d,7d).lowerQuartile, 0)
		Assert.assertEquals(7d, newArrayList(1d,2d,3d,4d,5d,6d,7d).upperQuartile, 0)

		Assert.assertEquals(1d, newArrayList(1d,2d,3d,4d,5d,6d).lowerQuartile, 0)
		Assert.assertEquals(6d, newArrayList(1d,2d,3d,4d,5d,6d).upperQuartile, 0)

		Assert.assertEquals(1d, newArrayList(1d,2d,3d,4d,5d).lowerQuartile, 0)
		Assert.assertEquals(5d, newArrayList(1d,2d,3d,4d,5d).upperQuartile, 0)

		Assert.assertEquals(1d, newArrayList(1d,2d,3d,4d).lowerQuartile, 0)
		Assert.assertEquals(4d, newArrayList(1d,2d,3d,4d).upperQuartile, 0)

		Assert.assertEquals(Double.NaN, newArrayList(1d,2d,3d).lowerQuartile, 0)
		Assert.assertEquals(Double.NaN, newArrayList(1d,2d,3d).upperQuartile, 0)

		Assert.assertEquals(Double.NaN, newArrayList(1d,2d).lowerQuartile, 0)
		Assert.assertEquals(Double.NaN, newArrayList(1d,2d).upperQuartile, 0)

		Assert.assertEquals(Double.NaN, newArrayList(1d).lowerQuartile, 0)
		Assert.assertEquals(Double.NaN, newArrayList(1d).upperQuartile, 0)

		Assert.assertEquals(Double.NaN, newArrayList().lowerQuartile, 0)
		Assert.assertEquals(Double.NaN, newArrayList().upperQuartile, 0)
	}
}