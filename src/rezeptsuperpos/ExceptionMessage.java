/*
 * Copyright (C) 2015  Hannes Hassler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rezeptsuperpos;

/**
 *
 * @author Hannes Hassler
 */
public class ExceptionMessage {
    public static String densityIsNull="cannot convert because Density=0.0";
    public static String noKnownIngredient="I dont know this ingredient";
    public static String couldnotSave="cannot save to File";
    public static String versionNo="version "+Version.no;
}
