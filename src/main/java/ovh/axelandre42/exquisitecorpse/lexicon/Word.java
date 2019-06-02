/* 
 * Copyright 2019 Alexandre Waeles
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to
 * do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ovh.axelandre42.exquisitecorpse.lexicon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alexandre Waeles
 *
 */
public class Word {
	private Map<String, Set<String>> variants = new HashMap<>();

	private String root;

	/**
	 * Set root word for this word instance.
	 * 
	 * @param root the root word
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	public String getRoot() {
		return root;
	}

	public boolean match(String word) {
		return variants.containsKey(word);
	}

	public void add(String word, Set<String> flags) {
		variants.put(word, flags);
	}

	public Set<Entry<String, Set<String>>> entries() {
		return variants.entrySet();
	}

	public List<Entry<String, Set<String>>> get(Set<String> constraints) {
		return variants.entrySet().parallelStream().filter(s -> s.getValue().containsAll(constraints))
				.collect(Collectors.toList());
	}
}
